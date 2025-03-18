package br.com.cresol.events.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="evento")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="nome", length=255, nullable=false)
	private String nome;

	@Column(name="data_inicio", nullable = false)
	private LocalDate dataInicial;

	@Column(name="data_final", nullable = false)
	private LocalDate dataFinal;

	@Column(name="ativo", nullable = false)
	private Boolean ativo;

	@ManyToOne
	@JoinColumn(name="instituicao_id")
	private Instituicao instituicaoId;
	
	public Evento() {
		
	}
	
	public Evento(String nome, LocalDate dataInicial, 
			LocalDate dataFinal, Boolean ativo, Instituicao instituicaoId) {
		this.nome = nome;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.ativo = ativo;
		this.instituicaoId = instituicaoId;
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
