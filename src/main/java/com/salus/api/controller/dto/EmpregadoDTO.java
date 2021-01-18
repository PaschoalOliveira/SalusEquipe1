package com.salus.api.controller.dto;

import com.salus.api.model.Empregado;

public class EmpregadoDTO {
	
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String cargo;
	private Double salario;
	
	
	public EmpregadoDTO(Empregado empregado) {
		this.id = empregado.getId();
		this.nome = empregado.getNome();
		this.cpf = empregado.getCpf();
		this.email = empregado.getEmail();
		this.cargo = empregado.getCargo();
		this.salario = empregado.getSalario();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public Double getSalario() {
		return salario;
	}


	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	
}
