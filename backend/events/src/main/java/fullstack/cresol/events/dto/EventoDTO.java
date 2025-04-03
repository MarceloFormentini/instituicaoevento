package fullstack.cresol.events.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import fullstack.cresol.events.config.LocalDateTimeDeserializer;
import fullstack.cresol.events.config.LocalDateTimeSerializer;
import fullstack.cresol.events.model.Evento;
import fullstack.cresol.events.model.Instituicao;
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
	@JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dataInicial;
	
	@NotNull(message="A data final não pode ser nula")
	@FutureOrPresent(message="A data final deve ser maior/igual a data atual")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dataFinal;

	private Boolean ativo;
	private Instituicao instituicao;
	
	public EventoDTO() {
		
	}
	
	public EventoDTO(Evento evento) {
		this.id = evento.getId();
		this.nome = evento.getNome();
		this.dataInicial = evento.getDataInicial();
		this.dataFinal = evento.getDataFinal();
		this.ativo = evento.getAtivo();
		this.instituicao = evento.getInstituicao();
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
}
