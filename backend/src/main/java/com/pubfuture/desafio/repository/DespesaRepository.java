package com.pubfuture.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pubfuture.desafio.model.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{

}
