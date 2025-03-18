package br.com.cresol.events.messaging;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.cresol.events.model.Evento;
import br.com.cresol.events.repository.EventoRepository;

@Service
public class EventoConsumer {

	@Autowired
	private EventoRepository eventoRepository;
	
	@KafkaListener(topics="eventos", groupId="grupo-eventos")
	public void processarEvento(Evento evento) {
		agendarDesativacao(evento);
	}

	private void agendarDesativacao(Evento evento) {
		long delay = calcularDelay(evento.getDataFinal()); 

		if (delay > 0) {
			new Thread(() -> {
				try {
					Thread.sleep(delay);
					evento.setAtivo(false);
					eventoRepository.save(evento);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}).start();
		}
	}

	private long calcularDelay(LocalDateTime dataFinal) {
		LocalDateTime agora = LocalDateTime.now();
		return dataFinal.isAfter(agora) ? java.time.Duration.between(agora, dataFinal).toMillis() : 0;
	}
}
