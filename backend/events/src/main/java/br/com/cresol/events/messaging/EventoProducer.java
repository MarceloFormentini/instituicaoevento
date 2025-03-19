package br.com.cresol.events.messaging;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import br.com.cresol.events.config.RabbitMQConfig;

@Service
public class EventoProducer {

	private final RabbitTemplate rabbitTemplate;
	
	public EventoProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void agendarInativacaoEvento(Integer eventoId, LocalDateTime dataFinal) {
		long delay = Duration.between(LocalDateTime.now(), dataFinal).toMillis();
		
		if (delay <= 0) {
			System.out.println("A data de fim já passou, inativando imediatamente.");
			rabbitTemplate.convertAndSend(RabbitMQConfig.EVENTO, eventoId);
			return;
		}
		
		Map<String, Object> mensagem = new HashMap<>();
		mensagem.put("eventoId", eventoId);
		
		rabbitTemplate.convertAndSend(RabbitMQConfig.EVENTO, mensagem);
	}
}
