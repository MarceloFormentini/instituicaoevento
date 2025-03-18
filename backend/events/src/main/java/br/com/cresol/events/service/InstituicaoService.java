package br.com.cresol.events.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cresol.events.exception.InstituicaoConflictException;
import br.com.cresol.events.exception.InstituicaoNotFoundException;
import br.com.cresol.events.exception.InstituicaoUsedException;
import br.com.cresol.events.model.Instituicao;
import br.com.cresol.events.repository.EventoRepository;
import br.com.cresol.events.repository.InstituicaoRepository;

@Service
public class InstituicaoService {

	@Autowired
	private InstituicaoRepository instituicaoRepository;
	
	@Autowired
	private EventoRepository eventoRepository;

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

	public List<Instituicao> getAllInstituicao() {
		return (List<Instituicao>) instituicaoRepository.findAll();
	}

	public Instituicao getInstituicao(Integer id) {
		Instituicao instituicao = instituicaoRepository.findById(id)
				.orElseThrow(() -> new InstituicaoNotFoundException("Não existe instituição cadastrada com o código " + id));

		return instituicao;
	}

	public Instituicao updateInstituicao(Instituicao novaInstituicao) {
		Instituicao instituicao = instituicaoRepository.findById(novaInstituicao.getId())
				.orElseThrow(() -> new InstituicaoNotFoundException("Não existe instituição cadastrada com o código " + novaInstituicao.getId()));

		instituicao.setNome(novaInstituicao.getNome());
		instituicao.setTipo(novaInstituicao.getTipo());
		
		instituicaoRepository.findByNomeAndTipo(
				novaInstituicao.getNome(),
				novaInstituicao.getTipo()
			).ifPresent(updateInstituicao -> {
				throw new InstituicaoConflictException(
					"Instituição " + novaInstituicao.getNome() + " - " + novaInstituicao.getTipo() + " já cadastrada."
				);
			});
		
		instituicaoRepository.save(instituicao);
		
		return instituicao;
	}

	public boolean removeInstituicao(Integer id) {
		Instituicao instituicao = instituicaoRepository.findById(id)
				.orElseThrow(() -> new InstituicaoNotFoundException("Não existe instituição cadastrada com o código " + id));
		
		eventoRepository.findByInstituicaoId(instituicao).ifPresent(evento -> {
			throw new InstituicaoUsedException(
				"Instituição " + instituicao.getNome() + " - " + instituicao.getTipo() + " já cadastrada."
			);
		});
		
		instituicaoRepository.delete(instituicao);
		return true;
	}

}
