package br.com.cresol.events.dto;

import java.time.LocalDate;

import br.com.cresol.events.model.Evento;
import br.com.cresol.events.model.Instituicao;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EventoDTO {

	private Integer id;
	
	@NotBlank(message="O nome não pod estar vazio")
	@Size(min=3, message="O nome deve ter no mínimo 3 caracteres")
	private String nome;
	
	@NotNull(message="A data de início não pode ser nula")
	@FutureOrPresent(message="A data de início deve ser maior/igual a data atual")
	private LocalDate dataInicial;
	
	@NotNull(message="A data final não pode ser nula")
	@FutureOrPresent(message="A data final deve ser maior/igual a data atual")
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
