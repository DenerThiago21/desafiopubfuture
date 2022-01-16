package com.pubfuture.desafio.controller;

import java.util.List;

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

import com.pubfuture.desafio.services.ContaService;
import com.pubfuture.desafio.services.ReceitaService;
import com.pubfuture.desafio.model.Conta;
import com.pubfuture.desafio.model.Receita;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

	ContaController contaController;
	ContaService contaService;
	ReceitaService receitaService;
	
	@Autowired
	public ReceitaController(ContaController contaController, ContaService contaService, ReceitaService receitaService) {
		this.contaController = contaController;
		this.contaService = contaService;
		this.receitaService = receitaService;
	}
	
	/** Cadastrar Receita */
	@PostMapping("/{conta_id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Receita addReceita(@PathVariable(value = "conta_id") Long conta_id, @RequestBody Receita receita) {
		
		return contaService.findById(conta_id).map(conta -> {
			double alteraSaldo = conta.getSaldo() + receita.getValor();
			
			conta.setSaldo(alteraSaldo);
			contaController.editarConta(conta_id, conta);
			
			receita.setConta(conta);
			
			return receitaService.addReceita(receita);
		}).orElseThrow();
	}
	
	/** Editar Receita */
	@PutMapping(value = "{id}/{conta_id}")
	public Receita editarReceita(@PathVariable("id") Long id, @PathVariable("conta_id") Long conta_id, @RequestBody Receita novaReceita) {
		Receita receita = receitaService.findById(id).orElseThrow();
		Conta conta = contaService.findById(conta_id).orElseThrow();
		
		double alteraSaldo = 0;
		
		if (novaReceita.getValor() < receita.getValor()) {
			double reajuste = receita.getValor() - novaReceita.getValor();
			alteraSaldo = receita.getConta().getSaldo() - reajuste;
		} else {
			double reajuste = novaReceita.getValor() - receita.getValor();
			alteraSaldo = receita.getConta().getSaldo() + reajuste;
		}
		
		conta.setSaldo(alteraSaldo);
		receita.setDataRecebimento(novaReceita.getDataRecebimento());
		receita.setDataRecebimentoEsperado(novaReceita.getDataRecebimentoEsperado());
		receita.setTipoReceita(novaReceita.getTipoReceita());
		receita.setDescricao(novaReceita.getDescricao());
		receita.setValor(novaReceita.getValor());
		receita.setConta(conta);
		
		contaService.addConta(conta);
		return receitaService.addReceita(receita);
	}
	
	/** Remover Receita */
	@DeleteMapping(value = "{id}")
	public String removeReceita(@PathVariable("id") Long id) {
		Receita receita = receitaService.findById(id).orElseThrow();
		
		receitaService.removeReceita(receita.getId());
		return "Receita removida com Sucesso";
	}
	
	/** Listar Receitas */
	@GetMapping
	public List<Receita> listarReceitas() {
		return receitaService.listarReceitas();
	}
	
}
