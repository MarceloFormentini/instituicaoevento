package br.com.cresol.events.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

	@Bean
	public NewTopic eventosTopic() {
		return new NewTopic("eventos", 1, (short) 1);
	}
}
