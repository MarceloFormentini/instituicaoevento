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
	private Integer institution_id;

	@NotBlank(message="O nome não pode estar vazio")
	@Size(min=3, message="O nome deve ter no mínimo 3 caracteres")
	@Column(name="nome", length=255, nullable=false)
	private String nome;

	@NotBlank(message="O tipo não pode estar vazio")
	@Size(min=3, message="O tipo deve ter no mínimo 3 caracteres")
	@Column(name="tipo", length=255, nullable=false)
	private String tipo;

	public Integer getInstitution_id() {
		return institution_id;
	}

	public void setInstitution_id(Integer institution_id) {
		this.institution_id = institution_id;
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
