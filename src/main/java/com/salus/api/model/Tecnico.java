package com.salus.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Tecnico")
public class Tecnico extends Empregado {

	private String especialidade;

	public Tecnico() {
	}

	public Tecnico(Long id, String nome, String cpf, String email, String matricula, String cargo, Double salario, String especialidade) {
		super(id, nome, cpf, email, matricula, cargo, salario);
		this.especialidade = especialidade;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	
}
