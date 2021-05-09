package com.company.shopping.kafka;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Kafka consumer class
 * 
 * @author Ajith
 *
 */
@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @KafkaListener(topics = "${kafka.topic.name}", groupId ="${spring.kafka.consumer.group-id}")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
}
