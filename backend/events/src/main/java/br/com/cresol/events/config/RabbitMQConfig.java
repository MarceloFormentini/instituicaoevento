package br.com.cresol.events.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	public static final String EVENTO = "evento_inativacao";
	
	@Bean
	public Queue eventoInativacao() {
		return new Queue(EVENTO, true);
	}
}
