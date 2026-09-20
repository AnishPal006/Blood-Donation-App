package com.anish.blooddonation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);
    private static final String TOPIC = "blood-requests";

    @Autowired
    private KafkaTemplate<String, Long> kafkaTemplate;

    public void publishBloodRequestEvent(Long requestId) {
        log.info(String.format("#### -> Producing message -> %s", requestId));
        kafkaTemplate.send(TOPIC, requestId);
    }
}
