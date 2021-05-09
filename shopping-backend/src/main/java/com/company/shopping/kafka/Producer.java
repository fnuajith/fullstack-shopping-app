package com.company.shopping.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.company.shopping.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Kafka producer
 * 
 * @author Ajith
 *
 */
@Service
public class Producer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Value("${kafka.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Product product) {
        logger.info(String.format("#### -> Producing message -> %s", toJSON(product)));
        // Ensures that the messages for same product always end up on the same partition
        this.kafkaTemplate.send(this.topicName, product.getId(), toJSON(product)); 
    }
    
    private String toJSON(Product product) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(product);
		} catch (JsonProcessingException e) {
			logger.error("Error when converting product object to JSON");
		}
    	return jsonString;
    }
}
