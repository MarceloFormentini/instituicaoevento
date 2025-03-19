package br.com.cresol.events.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cresol.events.model.Evento;
import br.com.cresol.events.model.Instituicao;

public interface EventoRepository extends JpaRepository<Evento, Integer>{

	@Query("select case when count(e) > 0 then true else false end from Evento e where e.instituicao.id = :instituicao")
	public boolean testeEventoVinculado(@Param("instituicao") Integer instituicao);

	@Query("select e from Evento e where e.instituicao.id = :instituicao and e.ativo = true")
	Page<Evento> findByInstituicao(@Param("instituicao") Integer instituicao, Pageable pageable);

	@Query("SELECT COUNT(e) > 0 FROM Evento e WHERE e.instituicao = :instituicao AND " +
		"(e.dataInicial BETWEEN :dataInicial AND :dataFinal OR e.dataFinal BETWEEN :dataInicial AND :dataFinal)")
	boolean existsByInstituicaoAndDataInicialBetweenOrDataFinalBetween(
		@Param("instituicao") Instituicao instituicao,
		@Param("dataInicial") LocalDateTime dataInicial,
		@Param("dataFinal") LocalDateTime dataFinal
	);
}
