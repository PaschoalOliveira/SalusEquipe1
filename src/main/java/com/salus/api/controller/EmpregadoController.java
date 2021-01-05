package com.salus.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.salus.api.model.Empregado;

@RestController
@RequestMapping("/v1/empregados")
public class EmpregadoController {

	@GetMapping
	public ResponseEntity<List<Empregado>> listar(){
		List<Empregado> empregados = new ArrayList<>();
		
		Empregado e1 = new Empregado(1L,"Yla","12345678910","yla@yla.yla","369258147","Programador Java Senior",8000.0);;
		Empregado e2 = new Empregado(2L,"Ivanilson Junior","9876543210","ivanilson@ivanilson.ivanilson","963852741","Engenheiro DevOps",8000.0);
		empregados.addAll(Arrays.asList(e1,e2));
		
		return ResponseEntity.ok(empregados);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Empregado> buscarPorId(@PathVariable Long id){
		return ResponseEntity.ok(null);
	}
	
	@PostMapping
	public ResponseEntity<String> salvar(Empregado empregado){
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand("1").toUri();
		return ResponseEntity.created(uri).body("Foi");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Empregado> atualizar(@PathVariable Long id){
		return ResponseEntity.ok(null);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Empregado> atualizarCampo(@PathVariable Long id){
		return ResponseEntity.ok(null);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		return ResponseEntity.noContent().build();
	}
}
