package br.com.cresol.events.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	// método genérico para criar o Map de resposta
	private Map<String, Object> createErrorResponse(HttpStatus status, String message){
		return Map.of(
			"timestamp", LocalDateTime.now(),
			"status", status.value(),
			"message", message
		);
	}

	// validação para instituição que já existe - valida nome e tipo
	@ExceptionHandler(InstituicaoConflictException.class)
	public ResponseEntity<Map<String, Object>> handleInstituicaoConflict(InstituicaoConflictException ex){
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(createErrorResponse(HttpStatus.CONFLICT, ex.getMessage()));
	}
	
	// validação para instituicao não encontrada
	@ExceptionHandler(InstituicaoNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleInstituicaoNotFound(InstituicaoNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
	}

	// tratamento para exceções inesperadas
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
	}
}
