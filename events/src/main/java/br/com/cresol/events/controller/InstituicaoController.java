package br.com.cresol.events.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cresol.events.dto.InstituicaoDTO;
import br.com.cresol.events.model.Instituicao;
import br.com.cresol.events.service.InstituicaoService;

@RestController
public class InstituicaoController {

	@Autowired
	private InstituicaoService service;

	@PostMapping("/instituicao")
	public ResponseEntity<?> addNewInstituicao(@RequestBody Instituicao novaInstituicao){

		Instituicao instuicaoSalva = service.addNewInstituicao(novaInstituicao);

		return ResponseEntity.ok(
			new InstituicaoDTO(
				instuicaoSalva.getNome(),
				instuicaoSalva.getTipo()
			)
		);
	}
	
	@GetMapping("/instituicao")
	public ResponseEntity<?> getAllInstituicao(){
		return service.getAllInstituicao();
	}
	
	@GetMapping("/instituicao/{id}")
	public ResponseEntity<?> getInstituicao(@PathVariable Integer id){
		return service.getInstituicao(id);
	}
}
