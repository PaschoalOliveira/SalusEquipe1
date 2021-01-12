package com.salus.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salus.api.controller.dto.EmpregadoDTO;
import com.salus.api.exception.ObjectNotFoundException;
import com.salus.api.model.Empregado;
import com.salus.api.repository.EmpregadoRepository;
import com.salus.api.service.iservice.IEmpregadoService;

@Service
public class EmpregadoService implements IEmpregadoService {

	@Autowired
	private EmpregadoRepository empregadoRepository;
	
	@Override
	public List<Empregado> listar() {
		return empregadoRepository.findAll();
	}

	@Override
	public Empregado buscarPorId(Long id) {		
		return empregadoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("O empregado não existe."));
	}

	@Override
	public Empregado salvar(Empregado empregado) {
		return empregadoRepository.save(empregado);
	}

	@Override
	public Empregado atualizar(Long id,Empregado empregado) {
		return empregadoRepository.findById(id)
	            .map(empregadoAtualizado -> {
	                empregadoAtualizado.setNome(empregado.getNome());
	                empregadoAtualizado.setCpf(empregado.getCpf());
	                empregadoAtualizado.setCargo(empregado.getCargo());
	                empregadoAtualizado.setEmail(empregado.getEmail());
	                empregadoAtualizado.setMatricula(empregado.getMatricula());
	                empregadoAtualizado.setSalario(empregado.getSalario());
	                
	            	return empregadoRepository.save(empregadoAtualizado);
	            }).orElseThrow(() -> new ObjectNotFoundException("O empregado não existe."));
	}

	@Override
	public Empregado atualizarCampo(Long id,Empregado empregado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Long id) {
		Empregado empregado = empregadoRepository.findById(id)
		.orElseThrow(() -> new ObjectNotFoundException("O empregado não existe."));
		empregadoRepository.delete(empregado);
	}

}
