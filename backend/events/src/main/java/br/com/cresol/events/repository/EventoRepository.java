package br.com.cresol.events.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.cresol.events.model.Evento;

public interface EventoRepository extends CrudRepository<Evento, Integer>{
}
