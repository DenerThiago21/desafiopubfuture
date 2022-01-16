package com.pubfuture.desafio.services;

import java.util.List;
import java.util.Optional;

import com.pubfuture.desafio.model.Receita;

public interface InterfaceReceita {
	List<Receita> listarReceitas();
	Receita addReceita(Receita receita);
	Optional<Receita> findById(Long id);
	void removeReceita(Long id);
}
