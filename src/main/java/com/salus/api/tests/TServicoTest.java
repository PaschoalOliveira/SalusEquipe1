package com.salus.api.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.salus.api.model.Servico;
import com.salus.api.model.Tecnico;
import com.salus.api.repository.ServicoRepository;

@RunWith(SpringRunner.class)
@DataJdbcTest

public class TServicoTest {

	@MockBean
	private ServicoRepository servicoRepository;

	@Before
	public void setUp() {
		Tecnico tecnico1 = new Tecnico(1l, "Maria", "345456", "maria@maria.com", "987875", "tecnica 2", 5000.00,
				"química");
		Tecnico tecnico2 = new Tecnico(2l, "Antonio", "43546", "antonio@antonio.com", "987655", "tecnico 1", 2600.00,
				"automação industrial");
		Servico servico1 = new Servico(1l, "Rua Amarela", "Teste de qualidade da água", tecnico1);
		Servico servico2 = new Servico(2l, "Rua do beija-flor", "Manutenção em decantador", tecnico2);

		List<Servico> servicos = new ArrayList<>();
		servicos.add(servico1);
		servicos.add(servico2);

		Mockito.when(servicoRepository.findAll()).thenReturn((servicos));

		Mockito.when(servicoRepository.findById(1l)).thenReturn(Optional.of(servico1));
		Mockito.when(servicoRepository.findById(2l)).thenReturn(Optional.of(servico2));

	}

	@Test
	public void findAll() {

		// dado que (contexto)
		Tecnico tecnico1 = new Tecnico(1l, "Maria", "345456", "maria@maria.com", "987875", "tecnica 2", 5000.00,
				"química");
		Tecnico tecnico2 = new Tecnico(2l, "Antonio", "43546", "antonio@antonio.com", "987655", "tecnico 1", 2600.00,
				"automação industrial");
		Servico servico1 = new Servico(1l, "Rua Amarela", "Teste de qualidade da água", tecnico1);
		Servico servico2 = new Servico(2l, "Rua do beija-flor", "Manutenção em decantador", tecnico2);

		List<Servico> servicos = new ArrayList<>();
		servicos.add(servico1);
		servicos.add(servico2);

		// quando isso ocorrer
		List<Servico> found = servicoRepository.findAll();

		// assegure que
		assertThat(found.size() == servicos.size());
	}

	@Test
	public void findById() {
		// dado que (contexto)
		Tecnico tecnico1 = new Tecnico(1l, "Maria", "345456", "maria@maria.com", "987875", "tecnica 2", 5000.00,
				"química");
		Tecnico tecnico2 = new Tecnico(2l, "Antonio", "43546", "antonio@antonio.com", "987655", "tecnico 1", 2600.00,
				"automação industrial");
		Servico servico1 = new Servico(1l, "Rua Amarela", "Teste de qualidade da água", tecnico1);
		Servico servico2 = new Servico(2l, "Rua do beija-flor", "Manutenção em decantador", tecnico2);

		List<Servico> servicos = new ArrayList<>();
		servicos.add(servico1);
		servicos.add(servico2);

		// quando isso ocorrer
		Optional<Servico> found = servicoRepository.findById(2l);

		// assegure que
		assertThat(found != null);
		assertThat(found.get().getTecnico().equals(servico2.getTecnico()));

	}
}
