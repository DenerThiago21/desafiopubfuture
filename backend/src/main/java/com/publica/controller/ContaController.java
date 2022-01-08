package com.publica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.publica.exeptions.ContaNotFoundException;
import com.publica.model.Conta;
import com.publica.repository.ContaRepository;
import com.publica.services.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	ContaService contaService;
	
	@Autowired
	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}
	
	@GetMapping
	public List<Conta> listarContas() {
		return contaService.listarContas(); 
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Conta addConta(@RequestBody Conta conta) {
		return contaService.addConta(conta);
	}
	
	@PutMapping(value="/{id}")
	public Conta editarConta(@PathVariable("id") Long id, @RequestBody Conta novaConta) {
		System.out.println(id);
		Conta conta = contaService.findById(id)
								  .orElseThrow(()->new ContaNotFoundException("Conta com código "+id+" não encontrada"));
		//System.out.println(conta.getId());
		conta.setInstituicaoFinanceira(novaConta.getInstituicaoFinanceira());
		conta.setTipoConta(novaConta.getTipoConta());
		conta.setSaldo(novaConta.getSaldo());
		return contaService.addConta(conta);
	}
	
	@DeleteMapping(value="/{id}")
	public String removeConta(@PathVariable("id") Long id) {
		Conta conta = contaService.findById(id)
								  .orElseThrow(()->new ContaNotFoundException("Conta com codigo "+id+" não encontrada"));
		contaService.removeConta(conta.getId());
		return "Conta com código "+id+" Removida com Sucesso!";
	}
	
}
