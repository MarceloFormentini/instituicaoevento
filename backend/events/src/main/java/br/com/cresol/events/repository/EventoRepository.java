package br.com.cresol.events.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cresol.events.dto.EventoDTO;
import br.com.cresol.events.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer>{

	@Query("select case when count(e) > 0 then true else false end from Evento e where e.instituicao.id = :instituicao")
	public boolean testeEventoVinculado(@Param("instituicao") Integer instituicao);
	
	@Query("select e from Evento e where e.instituicao.id = :instituicao and e.ativo = true")
	Page<Evento> findByInstituicao(@Param("instituicao") Integer instituicao, Pageable pageable);
	
//	Page<Evento> findAll(Pageable pageable);
}
