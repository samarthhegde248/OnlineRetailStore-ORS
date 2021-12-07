package com.ORS.OrdersApplication.message;

import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductsProducerService {
	private final Logger logger = LoggerFactory.getLogger(ProductsProducerService.class);
	private static final String TOPIC = "orders-products-topic";
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public void sendMessage(String key, String message) {
		logger.info("Kafka sending message -> topic: " + TOPIC + " -> key: " + key + " -> message: " + message);
		this.kafkaTemplate.send(TOPIC, key, message);
	}
	
}
