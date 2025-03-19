package br.com.cresol.events.model;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
	private Instituicao instituicao;
	
	public Evento() {
		
	}
	
	public Evento(String nome, LocalDateTime dataInicial, 
			LocalDateTime dataFinal, Boolean ativo, Instituicao instituicao) {
		this.nome = nome;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.ativo = ativo;
		this.instituicao = instituicao;
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

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("nome", nome)
			.append("dataInicial", dataInicial)
			.append("dataFinal", dataFinal)
			.append("ativo", ativo)
			.append("instituicao", instituicao != null ? instituicao.getId() : "null")
			.toString();
	}

	
}
