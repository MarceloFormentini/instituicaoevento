package br.com.cresol.events.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	public static final String EVENTO_INATIVACAO_QUEUE = "evento_inativacao_queue";
	
	@Bean
	public Queue eventoInativacao() {
		return new Queue(EVENTO_INATIVACAO_QUEUE, true);
	}
}
