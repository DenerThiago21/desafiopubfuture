package com.pubfuture.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pubfuture.desafio.exeptions.ContaNotFoundException;
import com.pubfuture.desafio.model.Conta;
import com.pubfuture.desafio.services.ContaService;


@RestController
@RequestMapping("/contas")
public class ContaController {
	
	ContaService contaService;
	
	@Autowired
	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}
	
	/** Cadastrar Conta */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Conta addConta(@RequestBody Conta conta) {
		return contaService.addConta(conta);
	}
	
	/** Editar Conta */
	@PutMapping(value="/{id}")
	public Conta editarConta(@PathVariable("id") Long id, @RequestBody Conta novaConta) {
		Conta conta = contaService.findById(id)
								  .orElseThrow(()->new ContaNotFoundException("Conta com código "+id+" não encontrada"));
		//System.out.println(conta.getId());
		conta.setInstituicaoFinanceira(novaConta.getInstituicaoFinanceira());
		conta.setTipoConta(novaConta.getTipoConta());
		conta.setSaldo(novaConta.getSaldo());
		return contaService.addConta(conta);
	}
	
	/** Remover Conta */
	@DeleteMapping(value="/{id}")
	public String removeConta(@PathVariable("id") Long id) {
		Conta conta = contaService.findById(id)
								  .orElseThrow(()->new ContaNotFoundException("Conta com codigo "+id+" não encontrada"));
		contaService.removeConta(conta.getId());
		return "Conta com código "+id+" Removida com Sucesso!";
	}
	
	/** Listar Contas */
	@GetMapping
	public List<Conta> listarContas() {
		return contaService.listarContas(); 
	}
	
	/** Transferir Saldo entre Contas */ 
	@PutMapping(value="/{idRemetente}/{idDestinatario}")
	public String transferenciaEntreContas(@PathVariable Long idRemetente, @PathVariable Long idDestinatario, @RequestBody ObjectNode json) {
		int valor = json.get("valor").asInt();
		Conta contaRemetente = contaService.findById(idRemetente)
										   .orElseThrow(()->new ContaNotFoundException("Conta Remetente com código "+idRemetente+" não encontrada"));
		Conta contaDestinatario = contaService.findById(idDestinatario)
											  .orElseThrow(()->new ContaNotFoundException("Conta Destinatario com código "+idDestinatario+" não encontrada"));
		
		if(contaRemetente.getSaldo() < valor) {
			return "Não foi possível fazer a transferencia ... Saldo insuficiente";
		} 
		
		contaRemetente.setInstituicaoFinanceira(contaRemetente.getInstituicaoFinanceira());
		contaRemetente.setTipoConta(contaRemetente.getTipoConta());
		contaRemetente.setSaldo(contaRemetente.getSaldo() - valor);
		contaService.addConta(contaRemetente);
		
		contaDestinatario.setInstituicaoFinanceira(contaDestinatario.getInstituicaoFinanceira());
		contaDestinatario.setTipoConta(contaDestinatario.getTipoConta());
		contaDestinatario.setSaldo(contaDestinatario.getSaldo() + valor);
		contaService.addConta(contaDestinatario);
		
		return "Transferência Efetuada com Sucesso!";
	}
	
	/** Listar saldo Total */
	@GetMapping(value="/saldoTotal")
	public int  saldoTotal() {
		List<Conta> contas = contaService.listarContas();
		int saldoTotal = 0;
		
		for(int i = 0; i < contas.size(); i++) {
			saldoTotal = saldoTotal + contas.get(i).getSaldo();
		}
		
		
		return saldoTotal;
	}
	
}
