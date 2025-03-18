package br.com.cresol.events.dto;

import br.com.cresol.events.model.Instituicao;

public class InstituicaoDTO {

	private Integer id;
	private String nome;
	private String tipo;
		
	public InstituicaoDTO(Instituicao instituicao) {
		this.id = instituicao.getId();
		this.nome = instituicao.getNome();
		this.tipo = instituicao.getTipo();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
