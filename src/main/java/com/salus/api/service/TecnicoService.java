package com.salus.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.salus.api.exception.ObjectNotFoundException;
import com.salus.api.model.Tecnico;
import com.salus.api.repository.TecnicoRepository;

@Service
public class TecnicoService {

	private static final String TECNICO_NAO_EXISTE = "O Técnico não existe.";

	@Autowired
	private TecnicoRepository tecnicoRepository;

	public Page<Tecnico> listar(Integer page, Integer linesPerPage, String orderBy, String direction, String nome, Double salarioMenor, Double salarioMaior) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return tecnicoRepository.findByNomeContainingAndSalarioBetween(nome, salarioMenor.doubleValue(), salarioMaior.doubleValue(), pageRequest);
	}
	
	public Tecnico salvar(Tecnico tecnico) {
		return tecnicoRepository.save(tecnico);
	}
	
	public void apagar(Long id) {
		Tecnico tecnico = tecnicoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(TECNICO_NAO_EXISTE));
		tecnicoRepository.delete(tecnico);
	}

	public Tecnico buscarPorId(Long id) {
	return tecnicoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(TECNICO_NAO_EXISTE));
		
	}

	public Tecnico editar(Long id, Tecnico tecnico) {
		
	 Tecnico tecnicoAtualizado = tecnicoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(TECNICO_NAO_EXISTE));
	 
	 tecnicoAtualizado.setEspecialidade(tecnico.getEspecialidade());
	 return tecnicoRepository.save(tecnicoAtualizado);
	}
}
