package br.com.cresol.events.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cresol.events.exception.InstituicaoConflictException;
import br.com.cresol.events.exception.InstituicaoNotFoundException;
import br.com.cresol.events.exception.InstituicaoUsedException;
import br.com.cresol.events.model.Instituicao;
import br.com.cresol.events.repository.EventoRepository;
import br.com.cresol.events.repository.InstituicaoRepository;

@Service
public class InstituicaoService {

	private final InstituicaoRepository instituicaoRepository;
	private final EventoRepository eventoRepository;

	public InstituicaoService(InstituicaoRepository instituicaoRepository, EventoRepository eventoRepository) {
		this.instituicaoRepository = instituicaoRepository;
		this.eventoRepository = eventoRepository;
	}

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

//	public List<Instituicao> getAllInstituicao() {
//		return (List<Instituicao>) instituicaoRepository.findAll();
//	}
	
	public Page<Instituicao> getAllInstituicao(Pageable pageable){
		return instituicaoRepository.findAll(pageable);
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

		if (!instituicaoRepository.existsById(id)) {
			throw new InstituicaoNotFoundException("Não existe instituição cadastrada com o código " + id);
		}

		boolean possuiEventos = eventoRepository.testeEventoVinculado(id);
        if (possuiEventos) {
            throw new InstituicaoUsedException("A instituição não pode ser excluída pois possui eventos vinculados.");
        }
		
		instituicaoRepository.deleteById(id);
		return true;
	}

}
