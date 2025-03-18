package br.com.cresol.events.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
	private LocalDateTime dataInicial;

	@Column(name="data_final", nullable = false)
	private LocalDateTime dataFinal;

	@Column(name="ativo", nullable = false)
	private Boolean ativo;

	@ManyToOne
	@JoinColumn(name="instituicao_id")
	private Instituicao instituicaoId;
	
	public Evento() {
		
	}
	
	public Evento(String nome, LocalDateTime dataInicial, 
			LocalDateTime dataFinal, Boolean ativo, Instituicao instituicaoId) {
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

	public LocalDateTime getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDateTime dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDateTime getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDateTime dataFinal) {
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
