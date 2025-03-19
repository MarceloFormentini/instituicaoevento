package br.com.cresol.events.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cresol.events.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer>{

	@Query("select case when count(e) > 0 then true else false end from Evento e where e.instituicaoId.id = :instituicaoId")
	public boolean testeEventoVinculado(@Param("instituicaoId") Integer instituicaoId);
	
	@Query("select e from Evento e where e.instituicaoId.id = :instituicaoId and e.ativo = true")
	List<Evento> findByInstituicaoId(@Param("instituicaoId") Integer instituicaoId);
}
