package br.com.cresol.events;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.cresol.events.controller.InstituicaoController;

@SpringBootTest
class EventsApplicationTests {

	@Autowired
    private InstituicaoController instituicaoController;

	@Test
	void contextLoads() {
		// verifica se a aplicação inicia corretamente
//		assertTrue(true);
		assertThat(instituicaoController).isNotNull();
	}

}
