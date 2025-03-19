package br.com.cresol.events.controller;

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

import br.com.cresol.events.dto.InstituicaoDTO;
import br.com.cresol.events.model.Instituicao;
import br.com.cresol.events.service.InstituicaoService;
import jakarta.validation.Valid;

@RestController
public class InstituicaoController {

	private final InstituicaoService service;

	public InstituicaoController(InstituicaoService service) {
		this.service = service;
	}

	@PostMapping("/instituicao")
	public ResponseEntity<?> addNewInstituicao(@Valid @RequestBody Instituicao novaInstituicao){

		Instituicao instuicaoSalva = service.addNewInstituicao(novaInstituicao);

		return ResponseEntity.ok(new InstituicaoDTO(instuicaoSalva));
	}
	
//	@GetMapping("/instituicao")
//	public ResponseEntity<?> getAllInstituicao(){
//		List<Instituicao> listaInstituicoes = service.getAllInstituicao();
//	    List<InstituicaoDTO> listaDTO = listaInstituicoes.stream()
//	        .map(instituicao -> new InstituicaoDTO(instituicao))
//	        .toList();
//	    return ResponseEntity.ok(listaDTO);
//	}
	
	@GetMapping("/instituicao")
	public ResponseEntity<Page<Instituicao>> getAllInstituicao(
		@PageableDefault(size=10, sort="id") Pageable pageable){
		return ResponseEntity.ok(service.getAllInstituicao(pageable));
	}
	
	
	@GetMapping("/instituicao/{id}")
	public ResponseEntity<?> getInstituicao(@PathVariable Integer id){
		Instituicao instituicao = service.getInstituicao(id);

		return ResponseEntity.ok(new InstituicaoDTO(instituicao));
	}
	
	 @PutMapping("/instituicao")
	 public ResponseEntity<?> updateInstituicao(@Valid @RequestBody Instituicao novaInstituicao){
		 
		 Instituicao instituicao = service.updateInstituicao(novaInstituicao);
		 
		 return ResponseEntity.ok(new InstituicaoDTO(instituicao));
	 }

	 @DeleteMapping("/instituicao/{id}")
	 public ResponseEntity<?> removeInstituicao(@PathVariable Integer id){
		 
		 if (service.removeInstituicao(id)) {
	            return ResponseEntity.noContent().build();
	        }
	        return ResponseEntity.notFound().build();
	 }
}
