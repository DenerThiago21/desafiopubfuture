package com.pubfuture.desafio.services;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pubfuture.desafio.model.Conta;
import com.pubfuture.desafio.repository.ContaRepository;

@Service
public class ContaService implements InterfaceConta {
	
	ContaRepository contaRepository;
	
	@Autowired
	public ContaService(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}
	
	@Override
	public List<Conta> listarContas() {
		return contaRepository.findAll();
	}
	
	@Override
	public Optional<Conta> findById(Long id){
		return contaRepository.findById(id);
	}
	
	@Override
	public Conta addConta(Conta conta) {
		return contaRepository.save(conta);
	}
	
	@Override
	public void removeConta(Long id) {
		contaRepository.deleteById(id);
	}
	

}
