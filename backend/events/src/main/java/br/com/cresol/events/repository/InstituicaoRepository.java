package br.com.cresol.events.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.cresol.events.model.Instituicao;

public interface InstituicaoRepository extends CrudRepository<Instituicao, Integer>{
	Optional<Instituicao> findByNomeAndTipo(String nome, String tipo);
}
