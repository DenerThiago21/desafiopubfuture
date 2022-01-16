package com.pubfuture.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pubfuture.desafio.model.Receita;
import com.pubfuture.desafio.repository.ReceitaRepository;

@Service
public class ReceitaService implements InterfaceReceita {

	ReceitaRepository receitaRepository;
	
	@Autowired
	public ReceitaService(ReceitaRepository receitaRepository) {
		this.receitaRepository = receitaRepository;
	}
	
	@Override
	public List<Receita> listarReceitas() {
		return receitaRepository.findAll();
	}
	
	@Override
	public Optional<Receita> findById(Long id) {
		return receitaRepository.findById(id);
	}
	
	@Override
	public Receita addReceita(Receita receita) {
		return receitaRepository.save(receita);
	}
	
	@Override
	public void removeReceita(Long id) {
		receitaRepository.deleteById(id);
	}
}
