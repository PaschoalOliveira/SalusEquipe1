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
import com.salus.api.model.Tecnico;
import com.salus.api.service.ServicoService;
import com.salus.api.service.TecnicoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/servicos")
public class ServicoController {

	@Autowired
	private ServicoService servicoService;
	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping
	@ApiOperation(value = "Retorna uma lista de serviços")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna uma lista de serviços"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		    })
	public ResponseEntity<Page<Servico>> listar(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "descricao") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "descricao", defaultValue = "") String descricao) {

		return ResponseEntity.ok(servicoService.listar(page, linesPerPage, orderBy, direction, descricao));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um serviço pelo id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna um serviço"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		    })
	public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(servicoService.buscarPorId(id));
	}

	@PostMapping
	@ApiOperation(value = "Persiste um serviço")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Serviço persistido"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		    })
	public ResponseEntity<Servico> salvar(@RequestBody Servico servico) {
		
		Tecnico tecnico = new Tecnico();
		tecnico = tecnicoService.buscarPorId(servico.getTecnico().getId());		
		servico.setTecnico(tecnico);
		
		return ResponseEntity.ok(servicoService.salvar(servico));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Edita um serviço pelo id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Serviço editado"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		    })
	public ResponseEntity<Servico> editar(@PathVariable Long id, @Valid @RequestBody Servico servico) {
		
		Tecnico tecnico = new Tecnico();
		tecnico = tecnicoService.buscarPorId(servico.getTecnico().getId());		
		servico.setTecnico(tecnico);
		
		return ResponseEntity.ok(servicoService.editar(id, servico));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um serviço pelo id")
	@ApiResponses(value = {
		    @ApiResponse(code = 204, message = "Serviço deletado"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		    })
	public ResponseEntity<?> apagar(@PathVariable Long id) {
		servicoService.apagar(id);
		return ResponseEntity.noContent().build();
	}

}
