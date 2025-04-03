package fullstack.cresol.events;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fullstack.cresol.events.controller.InstituicaoController;

@SpringBootTest
class EventsApplicationTests {

	private InstituicaoController instituicaoController;

	@BeforeEach
	void setUp(@Autowired InstituicaoController instituicaoController) {
		this.instituicaoController = instituicaoController;
	}

	@Test
	void contextLoads() {
		assertThat(instituicaoController).isNotNull();
	}
}
