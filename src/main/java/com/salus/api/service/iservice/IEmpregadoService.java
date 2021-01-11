package com.salus.api.service.iservice;

import java.util.List;

import com.salus.api.controller.dto.EmpregadoDTO;
import com.salus.api.model.Empregado;

public interface IEmpregadoService {

	List<Empregado> listar();
	
	Empregado buscarPorId(Long id);

	EmpregadoDTO salvar(Empregado empregado);
	
	Empregado atualizar(Long id,Empregado empregado);
	
	Empregado atualizarCampo(Long id,Empregado empregado);
	
	void deletar(Long id);
	
}
	
	
