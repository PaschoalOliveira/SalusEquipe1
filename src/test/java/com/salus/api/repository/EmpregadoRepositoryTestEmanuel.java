package com.salus.api.repository;

import static org.junit.Assert.*;

import com.salus.api.model.Empregado;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EmpregadoRepositoryTestEmanuel {

	@Resource
	private EmpregadoRepository empregadoRepository;

	@Before
	public void setup() {
		Empregado empregado1 = new Empregado(null,"Juliano","01826287522","juliano@hotmail.com","1","Gestor",8000d);
		Empregado empregado2 = new Empregado(null,"Fernando","01826287522","fernando@hotmail.com","5","Colaborador",10000d);
		Empregado empregado3 = new Empregado(null,"Maria","01826287522","maria@hotmail.com","5","Colaborador",8500d);
		Empregado empregado4 = new Empregado(null,"Mario","01826287522","mario@hotmail.com","5","Colaborador",3000d);

		empregadoRepository.saveAll(Arrays.asList(empregado1, empregado2, empregado3, empregado4));
	}

	@Test
	public void testFindByNomeContainingAndSalarioBetween() {
		var pageRequest = PageRequest.of(0, 10, Sort.Direction.valueOf("ASC"), "nome");
		var empregadosComNomeComecandoComMar = empregadoRepository.findByNomeContainingAndSalarioBetween("Mar", 0d, 1000000d, pageRequest);
		assertTrue(empregadosComNomeComecandoComMar.getContent().size() == 2);
	}

	@Test
	public void testFindByNomeContainingAndSalarioBetween_2() {
		var pageRequest = PageRequest.of(0, 10, Sort.Direction.valueOf("ASC"), "nome");
		var empregadosComSalarioEntre8000e10000 = empregadoRepository.findByNomeContainingAndSalarioBetween("", 8000d, 10000d, pageRequest);
		assertTrue(empregadosComSalarioEntre8000e10000.getContent().size() == 3);
	}

}
