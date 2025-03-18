package br.com.cresol.events.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.cresol.events.model.Evento;

@Service
public class EventoProducer {

	private static final String TOPIC = "eventos";
	
	private final KafkaTemplate<String, Evento> kafkaTemplate;
	
	public EventoProducer(KafkaTemplate<String, Evento> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void enviarEventoParaKafka(Evento evento) {
		kafkaTemplate.send(TOPIC, evento);
	}
}
