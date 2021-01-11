package com.salus.api.service.iservice;

import java.util.List;

import com.salus.api.model.Empregado;

public interface IEmpregadoService {

	List<Empregado> listar();
	
	Empregado buscarPorId(Long id);

	Empregado salvar(Empregado empregado);
	
	Empregado atualizar(Empregado empregado);
	
	Empregado atualizarCampo(Empregado empregado);
	
	void deletar(Long id);
	
}
	
	
