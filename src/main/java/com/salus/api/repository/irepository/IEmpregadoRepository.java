package com.salus.api.repository.irepository;

import java.util.List;

import com.salus.api.model.Empregado;

public interface IEmpregadoRepository {
	
	List<Empregado> listar();
	
	Empregado buscarPorId(Long id);
	
	Empregado salvar (Empregado empregado);
	
	Empregado atualizar (Empregado empregado);
	
	Empregado atualizarCampo (Empregado empregado);
	
	void deletar(Empregado empregado);

}
