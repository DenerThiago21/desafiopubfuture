package com.pubfuture.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pubfuture.desafio.model.Despesa;
import com.pubfuture.desafio.repository.DespesaRepository;

@Service
public class DespesaService implements InterfaceDespesa{
	
	DespesaRepository despesaRepository;
	
	@Autowired
	public DespesaService(DespesaRepository despesaRepository) {
		this.despesaRepository = despesaRepository;
	}
	
	@Override
	public List<Despesa> listarDespesas() {
		return despesaRepository.findAll();
	}
	
	@Override
	public Optional<Despesa> findById(Long id) {
		return despesaRepository.findById(id);
	}
	
	@Override
	public Despesa addDespesa(Despesa despesa) {
		return despesaRepository.save(despesa);
	}
	
	@Override
	public void removeDespesa(Long id) {
		despesaRepository.deleteById(id);
	}

}
