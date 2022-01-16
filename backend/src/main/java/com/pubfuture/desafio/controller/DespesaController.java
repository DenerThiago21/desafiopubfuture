package com.pubfuture.desafio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pubfuture.desafio.model.Conta;
import com.pubfuture.desafio.model.Despesa;
import com.pubfuture.desafio.services.ContaService;
import com.pubfuture.desafio.services.DespesaService;

@RestController
@RequestMapping("/despesas")
public class DespesaController {
	
	ContaController contaController;
	ContaService contaService;	
	DespesaService despesaService;
	
	
	
	@Autowired
	public DespesaController(ContaController contaController, ContaService contaService, DespesaService despesaService) {
		this.contaController = contaController;
		this.contaService = contaService;
		this.despesaService = despesaService;
	}
	
	/** Cadastrar Despesa */
	@PostMapping("/{conta_id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Despesa addDespesa(@PathVariable (value = "conta_id") Long conta_id, @RequestBody Despesa despesa) {
		
		return contaService.findById(conta_id).map(conta -> {
			double ateraSaldo = conta.getSaldo() - despesa.getValor();
			
			conta.setSaldo(ateraSaldo);
			contaController.editarConta(conta_id, conta);
			
			despesa.setConta(conta);
			
			return despesaService.addDespesa(despesa);
			
		}).orElseThrow();
	}
	
	/** Editar Despesa */
	@PutMapping(value="{id}/{conta_id}")
	public Despesa editarDespesa(@PathVariable ("id") Long id, @PathVariable ("conta_id") Long conta_id, @RequestBody Despesa novaDespesa) {
		Despesa despesa = despesaService.findById(id).orElseThrow();
		
		Conta conta = contaService.findById(conta_id).orElseThrow();
		
		double alteraSaldo = 0;
		
		if (novaDespesa.getValor() < despesa.getValor()) {
			double reajuste = despesa.getValor() - novaDespesa.getValor();
			alteraSaldo = despesa.getConta().getSaldo() + reajuste;
		} else {
			double reajuste = novaDespesa.getValor() - despesa.getValor();
			alteraSaldo = despesa.getConta().getSaldo() - reajuste;
		}
		
		conta.setSaldo(alteraSaldo);
		despesa.setDataPagamento(novaDespesa.getDataPagamento());
		despesa.setDataPagamentoEsperado(novaDespesa.getDataPagamentoEsperado());
		despesa.setTipoDespesa(novaDespesa.getTipoDespesa());
		despesa.setValor(novaDespesa.getValor());
		despesa.setConta(conta);
		
		contaService.addConta(conta);
		return despesaService.addDespesa(despesa);
		
	}
	
	/** Remover Despesa */
	@DeleteMapping(value="{id}")
	public String removeDespesa(@PathVariable("id") Long id) {
		Despesa despesa = despesaService.findById(id).orElseThrow();
		
		despesaService.removeDespesa(despesa.getId());
		return "Despesa Removida Com Sucesso!!";
	}
	
	/** Listar Despesas */
	@GetMapping
	public List<Despesa> listarDespesas() {
		return despesaService.listarDespesas();
	}
}
