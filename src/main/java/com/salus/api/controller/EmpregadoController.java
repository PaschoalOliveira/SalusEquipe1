package com.salus.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.salus.api.controller.dto.EmpregadoDTO;
import com.salus.api.model.Empregado;
import com.salus.api.service.EmpregadoService;

@RestController
@RequestMapping("/v1/empregados")
public class EmpregadoController {

	@Autowired
	private EmpregadoService empregadoService;
	
	@GetMapping
	public ResponseEntity<Page<Empregado>> listar(          
			@RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction,
            @RequestParam(value="nome", defaultValue="") String nome,
            @RequestParam(value = "salarioMenor", defaultValue="0") Double salarioMenor,
            @RequestParam(value = "salarioMaior", defaultValue = "100000") Double salaioMaior){
		return ResponseEntity.ok(empregadoService.listar(page, linesPerPage, orderBy, direction, nome, salarioMenor, salaioMaior));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmpregadoDTO> buscarPorId(@PathVariable Long id){
		return ResponseEntity.ok(new EmpregadoDTO(empregadoService.buscarPorId(id)));
	}
	
	@PostMapping
	public ResponseEntity<EmpregadoDTO> salvar(@RequestBody Empregado empregado){
		Empregado empregadoSalvo = empregadoService.salvar(empregado);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(empregadoSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(new EmpregadoDTO(empregadoSalvo));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Empregado> atualizar(@PathVariable Long id,@RequestBody Empregado empregado){
		return ResponseEntity.ok(empregadoService.atualizar(id, empregado));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Empregado> atualizarCampo(@PathVariable Long id){
		return ResponseEntity.ok(null);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		empregadoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
