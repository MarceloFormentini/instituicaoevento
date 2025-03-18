package br.com.cresol.events.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cresol.events.exception.EventoInstituicaoIncompativelException;
import br.com.cresol.events.exception.EventoNotFoundException;
import br.com.cresol.events.exception.InstituicaoNotFoundException;
import br.com.cresol.events.model.Evento;
import br.com.cresol.events.model.Instituicao;
import br.com.cresol.events.repository.EventoRepository;
import br.com.cresol.events.repository.InstituicaoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired InstituicaoRepository instituicaoRepository;

	public Evento addNewEvento(Integer instituicaoId, Evento novoEvento) {
		Instituicao instituicao = instituicaoRepository.findById(instituicaoId)
				.orElseThrow(() -> new InstituicaoNotFoundException("Não existe instituição cadastrada com o código " + instituicaoId));
		
		novoEvento.setInstituicaoId(instituicao);
		
		return eventoRepository.save(novoEvento);
	}

	public List<Evento> getAllEvento() {
		return (List<Evento>) eventoRepository.findAll();
	}

	public Evento getEvento(Integer id) {
		Evento evento = eventoRepository.findById(id)
				.orElseThrow(() -> new EventoNotFoundException("Não existe evento cadastrado com o código " + id));
				
		return evento;
	}

	public Evento updateEvento(Integer instituicaoId, Evento evento) {
		Instituicao instituicao = instituicaoRepository.findById(instituicaoId)
				.orElseThrow(() -> new InstituicaoNotFoundException("Não existe instituição cadastrada com o código " + instituicaoId));
		
		Evento eventoGravado = eventoRepository.findById(evento.getId())
				.orElseThrow(() -> new EventoNotFoundException("Não existe evento cadastrado com o código " + evento.getId()));
		
		if (!eventoGravado.getInstituicaoId().getId().equals(instituicaoId)) {
			throw new EventoInstituicaoIncompativelException("O evento não pertence a instituição especificada");
		}
		
		evento.setNome(evento.getNome());
		evento.setDataInicial(evento.getDataInicial());
		evento.setDataFinal(evento.getDataFinal());
		evento.setAtivo(evento.getAtivo());
		evento.setInstituicaoId(instituicao);
		
		return eventoRepository.save(evento);
	}

	public boolean removeEvento(Integer id) {
		
		return eventoRepository.findById(id).map(evento -> {
			eventoRepository.delete(evento);
			return true;
		}).orElseThrow(() -> new EventoNotFoundException("Não existe evento cadastrado com o código " + id));
	}

}
