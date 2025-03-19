package br.com.cresol.events.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cresol.events.dto.EventoDTO;
import br.com.cresol.events.exception.EventoDataIncorretaException;
import br.com.cresol.events.exception.EventoInstituicaoIncompativelException;
import br.com.cresol.events.exception.EventoNotFoundException;
import br.com.cresol.events.exception.InstituicaoNotFoundException;
import br.com.cresol.events.messaging.EventoProducer;
import br.com.cresol.events.model.Evento;
import br.com.cresol.events.repository.EventoRepository;
import br.com.cresol.events.repository.InstituicaoRepository;

@Service
public class EventoService {

	private final EventoRepository eventoRepository;
	private final InstituicaoRepository instituicaoRepository;
	private final EventoProducer eventoProducer;

	public EventoService(EventoRepository eventoRepository, InstituicaoRepository instituicaoRepository,
			EventoProducer eventoProducer) {
		this.eventoRepository = eventoRepository;
		this.instituicaoRepository = instituicaoRepository;
		this.eventoProducer = eventoProducer;
	}

	public Evento addNewEvento(Integer instituicao, EventoDTO evento) {

		if (!instituicaoRepository.existsById(instituicao)) {
			throw new InstituicaoNotFoundException("Não existe instituição cadastrada com o código " + instituicao);
		}

		if (evento.getDataFinal().isBefore(evento.getDataInicial())) {
			throw new EventoDataIncorretaException("A data final deve ser maior que a data inicial");
		}

		Evento newEvento = new Evento(
			evento.getNome(),
			evento.getDataInicial(), 
			evento.getDataFinal(),
			true,
			evento.getInstituicao()
		);
		
		Evento salvo = eventoRepository.save(newEvento);

		eventoProducer.enviarEventoParaKafka(salvo);

		return salvo;
	}

	public List<Evento> getEvento(Integer instituicao) {
		List<Evento> evento = eventoRepository.findByInstituicao(instituicao);

		return evento;
	}
	
	public Page<Evento> getEventos(Pageable pageable) {
	    return eventoRepository.findAll(pageable); // ✅ Pesquisa paginada
	}

	public Evento updateEvento(Integer instituicao, EventoDTO evento) {

		if (!instituicaoRepository.existsById(instituicao)) {
			throw new InstituicaoNotFoundException("Não existe instituição cadastrada com o código " + instituicao);
		}

		Evento eventoGravado = eventoRepository.findById(evento.getId())
			.orElseThrow(() -> new EventoNotFoundException(
				"Não existe evento cadastrado com o código " + evento.getId())
			);

		if (!eventoGravado.getInstituicao().getId().equals(instituicao)) {
			throw new EventoInstituicaoIncompativelException(
				"O evento não pertence a instituição especificada"
			);
		}

		if (evento.getDataFinal().isBefore(evento.getDataInicial())) {
			throw new EventoDataIncorretaException("A data final deve ser maior que a data inicial");
		}

		eventoGravado.setNome(evento.getNome());
		eventoGravado.setDataInicial(evento.getDataInicial());
		eventoGravado.setDataFinal(evento.getDataFinal());

		return eventoRepository.save(eventoGravado);
	}

	public boolean removeEvento(Integer id) {
		if (!eventoRepository.existsById(id)) {
			throw new InstituicaoNotFoundException("Não existe evento cadastrado com o código " + id);
		}

		eventoRepository.deleteById(id);
		return true;

	}

}
