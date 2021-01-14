package com.salus.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salus.api.model.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long>{

	Page<Tecnico> findByNomeContainingAndSalarioBetween(String nome, Double startSalario, Double endSalario, Pageable z);
}
