package com.ORS.ProductApplication.message;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.ORS.ProductApplication.entity.Inventory;
import com.ORS.ProductApplication.service.InventoryService;

@Service
public class ProductsConsumerService {
	private final Logger logger = LoggerFactory.getLogger(ProductsConsumerService.class);
	private static final String TOPIC = "orders-products-topic";
	
	@Autowired
	private InventoryService inventoryService;
	
    @KafkaListener(topics = TOPIC, groupId = "group-ORS-Product-Application", properties = "welcome")
    public void consume(ConsumerRecord<String, String> record) throws IOException {
    	logger.info("Kafka receiving message -> topic: " + TOPIC + " -> key: " + record.key() + " -> message: " + record.value());
    	if(record.key().equalsIgnoreCase("decrementProductQuantity")) {
    		this.decrementProductInventoryQuantity(record.value());
    	}
    }
    
//    https://stackoverflow.com/questions/58113829/receiving-kafka-key-in-spring-boot-kafka-listener
    
    public void decrementProductInventoryQuantity(String message){
    	String inventoryId = message.split("-")[0];
    	int orderedQuantity = Integer.parseInt(message.split("-")[1]);
    	Inventory inventory = this.inventoryService.getInventoryById(inventoryId);
    	if(inventory!=null) {
    		inventory.setQuantity(inventory.getQuantity()-orderedQuantity);
    		this.inventoryService.updateInventory(inventory);
    	}
    }
}
