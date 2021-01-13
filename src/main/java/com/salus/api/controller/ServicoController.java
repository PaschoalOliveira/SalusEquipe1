package com.salus.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salus.api.model.Servico;

import com.salus.api.service.ServicoService;

@RestController
@RequestMapping("/v1/servicos")
public class ServicoController {

	@Autowired
	private ServicoService servicoService;

	@GetMapping
	public ResponseEntity<Page<Servico>> listar(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "descricao") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "descricao", defaultValue = "") String descricao) {

		return ResponseEntity.ok(servicoService.listar(page, linesPerPage, orderBy, direction, descricao));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(servicoService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Servico> salvar(@RequestBody Servico servico) {
		return ResponseEntity.ok(servicoService.salvar(servico));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Servico> editar(@PathVariable Long id, @Valid @RequestBody Servico servico) {
		return ResponseEntity.ok(servicoService.editar(id, servico));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> apagar(@PathVariable Long id) {
		servicoService.apagar(id);
		return ResponseEntity.noContent().build();
	}

}
