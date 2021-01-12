package com.salus.api.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salus.api.model.Empregado;

@Repository
public interface EmpregadoRepository extends JpaRepository<Empregado, Long>{

	Page<Empregado> findByNomeContainingAndSalarioBetween(String nome, Double startSalario, Double endSalario, Pageable pageable);
}