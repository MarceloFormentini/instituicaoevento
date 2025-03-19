package br.com.cresol.events.messaging;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import br.com.cresol.events.config.RabbitMQConfig;
import br.com.cresol.events.service.EventoService;

@Service
public class EventoConsumer {

	private final EventoService eventoService;

	public EventoConsumer(EventoService eventoService) {
		this.eventoService = eventoService;
	}

	@RabbitListener(queues = RabbitMQConfig.EVENTO)
	public void processarInativacaoEvento(Map<String, Object> mensagem) {
		Integer eventoId = (Integer) mensagem.get("eventoId");

		if (eventoId == null) {
			System.err.println("ID do evento ausente na mensagem.");
			return;
		}

        System.out.println("Processando inativação do evento " + eventoId);
        eventoService.inativarEvento(eventoId);
    }

}
