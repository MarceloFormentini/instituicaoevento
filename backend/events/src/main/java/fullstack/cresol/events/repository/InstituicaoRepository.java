package fullstack.cresol.events.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import fullstack.cresol.events.model.Instituicao;

public interface InstituicaoRepository extends CrudRepository<Instituicao, Integer>{
	Optional<Instituicao> findByNomeAndTipo(String nome, String tipo);

	Page<Instituicao> findAll(Pageable pageable);
}
