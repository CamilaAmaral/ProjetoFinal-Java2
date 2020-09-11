package com.receita.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.receita.dominio.PF;

public interface PfRepository extends JpaRepository<PF, Integer> {
	
	PF findByCpf(String r);
}