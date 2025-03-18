package br.com.cresol.events.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.cresol.events.model.Evento;
import br.com.cresol.events.model.Instituicao;

public interface EventoRepository extends CrudRepository<Evento, Integer>{
	Optional<Evento> findByInstituicaoId(Instituicao instituicaoId);
}
