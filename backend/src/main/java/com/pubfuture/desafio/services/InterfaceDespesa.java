package com.pubfuture.desafio.services;

import java.util.List;
import java.util.Optional;

import com.pubfuture.desafio.model.Despesa;

public interface InterfaceDespesa {
	List<Despesa> listarDespesas();
	Optional<Despesa> findById(Long id);
	Despesa addDespesa(Despesa despesa);
	void removeDespesa(Long id);
}
