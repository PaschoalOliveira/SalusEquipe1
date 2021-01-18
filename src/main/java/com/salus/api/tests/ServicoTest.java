package com.salus.api.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.salus.api.model.Empregado;
import com.salus.api.model.Servico;
import com.salus.api.model.Tecnico;
import com.salus.api.repository.EmpregadoRepository;
import com.salus.api.repository.ServicoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ServicoTest {

	@Autowired
	private TestEntityManager entityManager;

	@MockBean
	private ServicoRepository servicoRepository;
	
	 @Before
	    public void setUp() {
		 	Tecnico t = new Tecnico();
	        Servico servico1 = new Servico(1l, "endereco", "descrição", t);
	        Servico servico2 = new Servico(2l, "endereço","descrição", t);
	        
	        List<Servico> servicos = new ArrayList<>();
	        servicos.add(servico1);
	        servicos.add(servico2);
	        
	        Mockito.when(servicoRepository.findById(1l)).thenReturn(Optional.of(servico1));
	        
	    }
	
	@Test
	public void findById() {
		Tecnico t = new Tecnico();
		Servico servico = new Servico(2l,"endereco", "descricao", t);
		
		Servico busca = servicoRepository.findById(2l).get();
		
		 assertThat(busca.getDescricao()).isEqualTo("endereco");
	}

}
