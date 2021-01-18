package com.salus.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.salus.api.exception.ObjectNotFoundException;
import com.salus.api.model.Servico;
import com.salus.api.repository.ServicoRepository;

@Service
public class ServicoService {

	private static final String SERVICO_NAO_EXISTE = "O Serviço não existe.";

	@Autowired
	private ServicoRepository servicoRepository;

	public Page<Servico> listar(Integer page, Integer linesPerPage, String orderBy, String direction, String descricao) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return servicoRepository.findAll(pageRequest);
	}
	
	public Servico salvar(Servico servico) {
		return servicoRepository.save(servico);
	}
	
	public void apagar(Long id) {
		Servico servico = servicoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(SERVICO_NAO_EXISTE));
		servicoRepository.delete(servico);
	}

	public Servico buscarPorId(Long id) {
	return servicoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(SERVICO_NAO_EXISTE));
		
	}

	public Servico editar(Long id, Servico novoServico) {
		 Servico servicoAntigo = servicoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(SERVICO_NAO_EXISTE));
		 servicoAntigo.setEndereco(novoServico.getEndereco());
		 servicoAntigo.setDescricao(novoServico.getDescricao());
		 servicoAntigo.setTecnico(novoServico.getTecnico());
		return servicoRepository.save(novoServico);
	}
}
