package com.salus.api.service.iservice;


import org.springframework.data.domain.Page;

import com.salus.api.model.Empregado;

public interface IEmpregadoService {

	public Page<Empregado> listar(Integer page, Integer linesPerPage, String orderBy, String direction, String nome, Double salarioMaior, Double salarioMenor);
	
	Empregado buscarPorId(Long id);

	Empregado salvar(Empregado empregado);
	
	Empregado atualizar(Long id,Empregado empregado);
	
	Empregado atualizarCampo(Long id,Empregado empregado);
	
	void deletar(Long id);
	
}
	
	
