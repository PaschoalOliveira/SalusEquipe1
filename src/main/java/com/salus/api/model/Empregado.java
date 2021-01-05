package com.salus.api.model;

public class Empregado {

	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String matricula;
	private String cargo;
	private Double salario;
	
	public Empregado() {
		
	}
	
	public Empregado(Long id, String nome, String cpf, String email, String matricula, String cargo, Double salario) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.matricula = matricula;
		this.cargo = cargo;
		this.salario = salario;
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
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
