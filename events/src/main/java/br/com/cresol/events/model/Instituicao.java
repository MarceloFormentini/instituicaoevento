package br.com.cresol.events.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="instituicao")
public class Instituicao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotBlank(message="O nome não pode estar vazio")
	@Size(min=3, message="O nome deve ter no mínimo 3 caracteres")
	@Column(name="nome", length=255, nullable=false)
	private String nome;

	@NotBlank(message="O tipo não pode estar vazio")
	@Size(min=3, message="O tipo deve ter no mínimo 3 caracteres")
	@Column(name="tipo", length=255, nullable=false)
	private String tipo;

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
