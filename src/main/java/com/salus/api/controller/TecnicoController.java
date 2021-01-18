package com.salus.api.controller;

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

import com.salus.api.model.Tecnico;

import com.salus.api.service.TecnicoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/v1/tecnicos")
public class TecnicoController {

	@Autowired
	private TecnicoService tecnicoService;
	
	@GetMapping
	@ApiOperation(value = "Retorna uma lista de tecnicos")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna uma lista de tecnicos"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		    })
	public ResponseEntity<Page<Tecnico>> listar(
			@RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction,
            @RequestParam(value="nome", defaultValue="") String nome,
            @RequestParam(value = "salarioMenor", defaultValue="0") Double salarioMenor,
            @RequestParam(value = "salarioMaior", defaultValue = "100000") Double salaioMaior) {
		
		return ResponseEntity.ok(tecnicoService.listar(page, linesPerPage, orderBy, direction, nome, salarioMenor, salaioMaior));
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um tecnico pelo id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna um Tecnico"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		    })
	public ResponseEntity<Tecnico> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(tecnicoService.buscarPorId(id));
	}
	
	@PostMapping
	@ApiOperation(value = "Persiste um tecnico pelo id")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Tecnico persistido"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		    })
	public ResponseEntity<Tecnico> salvar(@RequestBody Tecnico tecnico){
		return ResponseEntity.ok(tecnicoService.salvar(tecnico));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Edita um tecnico pelo id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Tecnico editado"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		    })
	public ResponseEntity<Tecnico> editar(@PathVariable Long id, @RequestBody Tecnico tecnico) {
		return ResponseEntity.ok(tecnicoService.editar(id, tecnico));
	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um tecnico pelo id")
	@ApiResponses(value = {
		    @ApiResponse(code = 204, message = "Tecnico deletado"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		    })
	public ResponseEntity<?> apagar(@PathVariable Long id) {
		tecnicoService.apagar(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
}
