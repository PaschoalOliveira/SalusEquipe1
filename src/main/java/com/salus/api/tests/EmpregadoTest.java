package com.salus.api.tests;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.salus.api.model.Empregado;
import com.salus.api.repository.EmpregadoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmpregadoTest {

    @Autowired
    //Provê os mesmos métodos do JPA 
    private TestEntityManager entityManager;

    @MockBean
    private EmpregadoRepository empregadoRepository;

    @Before
    public void setUp() {
        Empregado empregado1 = new Empregado(6l,"Juliano","01826287522","juliano@hotmail.com","1","Gestor",9000d);
        Empregado empregado2 = new Empregado(8l,"Fernando","01826287522","fernando@hotmail.com","5","Colaborador",5000d);
        
        List<Empregado> empregados = new ArrayList<Empregado>();
        empregados.add(empregado1);
        empregados.add(empregado2);
        PageRequest pageRequest = PageRequest.of(1, 1, Sort.Direction.valueOf("ASC"), "nome");
        
        Mockito.when(empregadoRepository.findByNomeContainingAndSalarioBetween("Juliano", 2000d, 8000d, pageRequest)).
        thenReturn(new PageImpl<>(empregados));
        
        Mockito.when(empregadoRepository.findById(6l)).thenReturn(Optional.of(empregado1));
        
    }
    
	@Test
    public void findById() {
        // given
        Empregado empregado = new Empregado(6l,"Juliano","01826287522","juliano@hotmail.com","1","Gestor",9000d);

        // when
        Empregado found = empregadoRepository.findById(6l).get();

        // then
        assertThat(found.getNome()).isEqualTo(empregado.getNome());
    }
	
	@Test
    public void findByNomeSalario() {
        // given
        Empregado empregado = new Empregado(5l,"Rafael","01826287543","rafa@hotmail.com","2","Analista",8000d);
        Empregado empregado2 = new Empregado(8l,"Fernando","01826287522","fernando@hotmail.com","5","Colaborador",5000d);
        
        List<Empregado> empregados = new ArrayList<Empregado>();
        empregados.add(empregado);
        empregados.add(empregado2);
        PageRequest pageRequest = PageRequest.of(1, 1, Sort.Direction.valueOf("ASC"), "nome");

        // when
        Page<Empregado> found = empregadoRepository.findByNomeContainingAndSalarioBetween("Juliano", 2000d, 8000d, pageRequest);

        // then
        assertThat(found.getNumberOfElements()).isEqualTo(empregados.size());
    }

}
