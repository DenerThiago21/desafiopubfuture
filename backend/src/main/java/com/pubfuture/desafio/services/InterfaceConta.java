package com.pubfuture.desafio.services;

import java.util.List;
import java.util.Optional;

import com.pubfuture.desafio.model.Conta;

public interface InterfaceConta {
	List<Conta> listarContas();
	Conta addConta(Conta conta);
	Optional<Conta> findById(Long id);
	void removeConta(Long id);
}
