package br.com.fasttrack.desafio.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.fasttrack.desafio.dto.OrderDTO;

@Service
public class OrderProducer {

	private final String topic;
	private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

	public OrderProducer(@Value("${topic.name}") String topic, KafkaTemplate<String, OrderDTO> kafkaTemplate) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}

	public void send(OrderDTO orderDTO) {
		kafkaTemplate.send(topic, orderDTO);
	}
}
