package com.publica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.publica.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

}
