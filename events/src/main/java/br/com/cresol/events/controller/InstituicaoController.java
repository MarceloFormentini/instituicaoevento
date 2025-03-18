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
				instuicaoSalva.getId(),
				instuicaoSalva.getNome(),
				instuicaoSalva.getTipo()
			)
		);
	}
	
	@GetMapping("/instituicao")
	public ResponseEntity<?> getAllInstituicao(){
		List<Instituicao> listaInstituicoes = service.getAllInstituicao();
	    List<InstituicaoDTO> listaDTO = listaInstituicoes.stream()
	        .map(instituicao -> new InstituicaoDTO(
	        	instituicao.getId(),
	            instituicao.getNome(),
	            instituicao.getTipo()
	        ))
	        .toList();
	    return ResponseEntity.ok(listaDTO);
	}
	
	@GetMapping("/instituicao/{id}")
	public ResponseEntity<?> getInstituicao(@PathVariable Integer id){
		Instituicao instituicao = service.getInstituicao(id);

		return ResponseEntity.ok(
				new InstituicaoDTO(
					instituicao.getId(),
					instituicao.getNome(),
					instituicao.getTipo()
				)
			);
	}
	
}
