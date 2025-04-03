package fullstack.cresol.events.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fullstack.cresol.events.dto.EventoDTO;
import fullstack.cresol.events.model.Evento;
import fullstack.cresol.events.service.EventoService;
import jakarta.validation.Valid;

@RestController
public class EventoController {

	private final EventoService service;

	public EventoController(EventoService service) {
		this.service = service;
	}

	@PostMapping("/evento/{instituicao}")
	public ResponseEntity<?> addNewEvento(@Valid @RequestBody EventoDTO evento,
			@PathVariable Integer instituicao) {
		Evento eventoSalvo = service.addNewEvento(instituicao, evento);
		
		return ResponseEntity.ok(new EventoDTO(eventoSalvo));
	}
	
	@GetMapping("/evento/{instituicao}")
    public ResponseEntity<Page<EventoDTO>> getEventos(
        @PageableDefault(size = 10, sort = "dataInicial") Pageable pageable,
        @PathVariable Integer instituicao) {
        return ResponseEntity.ok(service.getEvento(pageable, instituicao));
    }
	
	@GetMapping("/evento/{instituicao}/{id}")
	public ResponseEntity<?> getEventoId(@PathVariable Integer instituicao, @PathVariable Integer id){
		Evento evento = service.getEventoId(instituicao, id);
		
		return ResponseEntity.ok(new EventoDTO(evento));
	}
	
	@PutMapping("/evento/{instituicao}")
	public ResponseEntity<?> updateEvent(@Valid @RequestBody EventoDTO evento,
			@PathVariable Integer instituicao){
		Evento eventoAlterado = service.updateEvent(instituicao, evento);

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
