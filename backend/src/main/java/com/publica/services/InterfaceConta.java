package com.publica.services;

import java.util.List;
import java.util.Optional;

import com.publica.model.Conta;

public interface InterfaceConta {
	List<Conta> listarContas();
	Conta addConta(Conta conta);
	Optional<Conta> findById(Long id);
	void removeConta(Long id);
}
