package br.com.cresol.events.dto;

import java.time.LocalDate;

import br.com.cresol.events.model.Evento;
import br.com.cresol.events.model.Instituicao;

public class EventoDTO {

	private Integer id;
	private String nome;
	private LocalDate dataInicial;
	private LocalDate dataFinal;
	private Boolean ativo;
	private Instituicao instituicaoId;
	
	public EventoDTO(Evento evento) {
		this.id = evento.getId();
		this.nome = evento.getNome();
		this.dataInicial = evento.getDataInicial();
		this.dataFinal = evento.getDataFinal();
		this.ativo = evento.getAtivo();
		this.instituicaoId = evento.getInstituicaoId();
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

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Instituicao getInstituicaoId() {
		return instituicaoId;
	}

	public void setInstituicaoId(Instituicao instituicaoId) {
		this.instituicaoId = instituicaoId;
	}
}
