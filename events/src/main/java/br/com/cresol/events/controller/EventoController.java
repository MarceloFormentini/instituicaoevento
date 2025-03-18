package br.com.cresol.events.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cresol.events.dto.EventoDTO;
import br.com.cresol.events.model.Evento;
import br.com.cresol.events.service.EventoService;
import jakarta.validation.Valid;

@RestController
public class EventoController {

	@Autowired
	private EventoService service;

	@PostMapping("/evento/{instituicaoId}")
	public ResponseEntity<?> addNewEvento(@Valid @RequestBody EventoDTO evento,
			@PathVariable Integer instituicaoId) {
		Evento eventoSalvo = service.addNewEvento(instituicaoId, evento);
		
		return ResponseEntity.ok(new EventoDTO(eventoSalvo));
	}
	
	@GetMapping("/evento")
	public ResponseEntity<?> getAllEvento(){
		List<Evento> listaEventos = service.getAllEvento();
		List<EventoDTO> listaDTO = listaEventos.stream()
			.map(evento -> new EventoDTO(evento))
			.toList();
		
		return ResponseEntity.ok(listaDTO);
	}
	
	@GetMapping("evento/{id}")
	public ResponseEntity<?> getEvento(@PathVariable Integer id){
		Evento evento = service.getEvento(id);
		
		return ResponseEntity.ok(new EventoDTO(evento));
	}
	
	@PutMapping("/evento/{instituicaoId}")
	public ResponseEntity<?> updateEvento(@Valid @RequestBody Evento evento,
			@PathVariable Integer instituicaoId){
		Evento eventoAlterado = service.updateEvento(instituicaoId, evento);

		return ResponseEntity.ok(new EventoDTO(eventoAlterado));
	}
	
	@DeleteMapping("/evento/{id}")
	public ResponseEntity<?> removeEvento(@PathVariable Integer id){
		if (service.removeEvento(id)) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
