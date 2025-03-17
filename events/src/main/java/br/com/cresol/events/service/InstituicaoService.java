package br.com.cresol.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cresol.events.exception.InstituicaoConflictException;
import br.com.cresol.events.model.Instituicao;
import br.com.cresol.events.repository.InstituicaoRepository;

@Service
public class InstituicaoService {

	@Autowired
	private InstituicaoRepository instituicaoRepository;

	public Instituicao addNewInstituicao(Instituicao novaInstituicao) {

		instituicaoRepository.findByNomeAndTipo(
			novaInstituicao.getNome(),
			novaInstituicao.getTipo()
		).ifPresent(instituicao -> {
			throw new InstituicaoConflictException(
				"Instituição " + novaInstituicao.getNome() + " - " + novaInstituicao.getTipo() + " já cadastrada."
			);
		});

		return instituicaoRepository.save(novaInstituicao);
	}

	public ResponseEntity<?> getAllInstituicao() {
		return (ResponseEntity<?>) instituicaoRepository.findAll();
	}

	public ResponseEntity<?> getInstituicao(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
