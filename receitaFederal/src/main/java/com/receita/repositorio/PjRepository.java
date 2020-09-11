package com.receita.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.receita.dominio.PJ;

public interface PjRepository extends JpaRepository<PJ, Integer> {

	PJ findByCnpj(String r);
}