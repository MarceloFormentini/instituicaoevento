package br.com.cresol.events.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cresol.events.dto.EventoDTO;
import br.com.cresol.events.exception.EventoDataIncorretaException;
import br.com.cresol.events.exception.EventoInstituicaoIncompativelException;
import br.com.cresol.events.exception.EventoNotFoundException;
import br.com.cresol.events.exception.InstituicaoNotFoundException;
import br.com.cresol.events.messaging.EventoProducer;
import br.com.cresol.events.model.Evento;
import br.com.cresol.events.model.Instituicao;
import br.com.cresol.events.repository.EventoRepository;
import br.com.cresol.events.repository.InstituicaoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired 
	private InstituicaoRepository instituicaoRepository;
	
	@Autowired
	private EventoProducer eventoProducer;

	public Evento addNewEvento(Integer instituicaoId, EventoDTO evento) {
		Instituicao instituicao = instituicaoRepository.findById(instituicaoId)
			.orElseThrow(() -> new InstituicaoNotFoundException(
				"Não existe instituição cadastrada com o código " + instituicaoId)
			);
		
		if (evento.getDataFinal().isBefore(evento.getDataInicial())) {
			throw new EventoDataIncorretaException("A data final deve ser maior que a data inicial");
		}
		
		Evento newEvento = new Evento(
			evento.getNome(),
			evento.getDataInicial(), 
			evento.getDataFinal(),
			true,
			instituicao
		);
		
		Evento salvo = eventoRepository.save(newEvento);
		
//		eventoProducer.enviarEventoParaKafka(salvo);
		
		return salvo;
	}

	public List<Evento> getAllEvento() {
		return (List<Evento>) eventoRepository.findAll();
	}

	public Evento getEvento(Integer id) {
		Evento evento = eventoRepository.findById(id)
				.orElseThrow(() -> new EventoNotFoundException("Não existe evento cadastrado com o código " + id));
				
		return evento;
	}

	public Evento updateEvento(Integer instituicaoId, EventoDTO evento) {
		Instituicao instituicao = instituicaoRepository.findById(instituicaoId)
			.orElseThrow(() -> new InstituicaoNotFoundException(
				"Não existe instituição cadastrada com o código " + instituicaoId)
			);
		
		Evento eventoGravado = eventoRepository.findById(evento.getId())
			.orElseThrow(() -> new EventoNotFoundException(
				"Não existe evento cadastrado com o código " + evento.getId())
			);
		
		if (!eventoGravado.getInstituicaoId().getId().equals(instituicaoId)) {
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
		
		return eventoRepository.findById(id).map(evento -> {
			eventoRepository.delete(evento);
			return true;
		}).orElseThrow(() -> new EventoNotFoundException("Não existe evento cadastrado com o código " + id));
	}

}
