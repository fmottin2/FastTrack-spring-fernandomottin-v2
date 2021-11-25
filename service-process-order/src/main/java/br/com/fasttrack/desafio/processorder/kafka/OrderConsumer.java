package br.com.fasttrack.desafio.processorder.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.fasttrack.desafio.processorder.dto.OrderDTO;
import br.com.fasttrack.desafio.processorder.kafka.service.OrderService;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class OrderConsumer {

	@Autowired
	OrderService service;
	
	@Value(value = "${topic.process.name}")
	private String topic;

	@Value(value = "${spring.kafka.group-id}")
	private String groupId;

	@KafkaListener(topics = "${topic.process.name}", groupId = "${spring.kafka.group-id}", containerFactory = "orderKafkaListenerContainerFactory")
	public void listenTopicORDER(ConsumerRecord<String, OrderDTO> record) {
		log.info("Consumer register, OrderId: "+record.value().getId());
		
		service.process(record.value());
		
		log.info("Order processed, OrderId: "+record.value().getId());
	}
}
