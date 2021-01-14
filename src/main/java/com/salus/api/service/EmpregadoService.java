package com.salus.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.salus.api.exception.ObjectNotFoundException;
import com.salus.api.model.Empregado;
import com.salus.api.repository.EmpregadoRepository;
import com.salus.api.service.iservice.IEmpregadoService;

@Service
public class EmpregadoService implements IEmpregadoService {

	private static final String EMPREGADO_NAO_EXISTE = "O empregado não existe.";

	@Autowired
	private EmpregadoRepository empregadoRepository;
	
	@Override
	public Page<Empregado> listar(Integer page, Integer linesPerPage, String orderBy, String direction, String nome, Double salarioMenor, Double salarioMaior) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return empregadoRepository.findByNomeContainingAndSalarioBetween(nome, salarioMenor.doubleValue(), salarioMaior.doubleValue(), pageRequest);
	}

	@Override
	public Empregado buscarPorId(Long id) {		
		return empregadoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(EMPREGADO_NAO_EXISTE));
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
	            }).orElseThrow(() -> new ObjectNotFoundException(EMPREGADO_NAO_EXISTE));
	}

	@Override
	public Empregado atualizarCampo(Long id,Empregado empregado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Long id) {
		Empregado empregado = empregadoRepository.findById(id)
		.orElseThrow(() -> new ObjectNotFoundException(EMPREGADO_NAO_EXISTE));
		empregadoRepository.delete(empregado);
	}

}
