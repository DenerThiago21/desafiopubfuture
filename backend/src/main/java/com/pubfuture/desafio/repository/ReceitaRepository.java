package com.pubfuture.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pubfuture.desafio.model.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{

}
