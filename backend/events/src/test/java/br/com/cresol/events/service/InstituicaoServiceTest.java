package br.com.cresol.events.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.cresol.events.exception.InstituicaoConflictException;
import br.com.cresol.events.model.Instituicao;
import br.com.cresol.events.repository.InstituicaoRepository;

public class InstituicaoServiceTest {
	
	@Mock
	private InstituicaoRepository instituicaoRepository;
	
	@InjectMocks
	private InstituicaoService instituicaoService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void addNewInstituicao() {
		Instituicao instituicao = new Instituicao(null, "Cresol", "Confederação");
		
		// Simulamos como o banco de dados geraria um ID automaticamente
	    Instituicao instituicaoComId = new Instituicao(1, "Cresol", "Confederação");

		when(instituicaoRepository.save(any(Instituicao.class))).thenReturn(instituicaoComId);

		Instituicao resultado = instituicaoService.addNewInstituicao(instituicao);
		
		assertNotNull(resultado);
		assertNotNull(resultado.getId());
		assertEquals("Cresol", resultado.getNome());
		verify(instituicaoRepository, times(1)).save(instituicao);
	}
	
	@Test
	void addNewInstituicaoComMesmoNomeTipo() {
		Instituicao instituicao = new Instituicao(null, "Cresol", "Financeiro");
		
		when(instituicaoRepository.findByNomeAndTipo("Cresol", "Financeiro")).thenReturn(Optional.of(instituicao));
		Exception exception = assertThrows(InstituicaoConflictException.class, () -> {
            instituicaoService.addNewInstituicao(instituicao);
        });
		
		String msg = "Instituição " + instituicao.getNome() + " - " + instituicao.getTipo() + " já cadastrada.";
		assertEquals(msg, exception.getMessage());
		verify(instituicaoRepository, never()).save(instituicao);
	}
}
