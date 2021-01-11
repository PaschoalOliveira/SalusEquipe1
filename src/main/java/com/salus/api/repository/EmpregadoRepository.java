package com.salus.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salus.api.model.Empregado;

@Repository
public interface EmpregadoRepository extends JpaRepository<Empregado, Long>{

}
