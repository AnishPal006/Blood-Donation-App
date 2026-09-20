package com.anish.blooddonation.service;

import com.anish.blooddonation.model.BloodRequest;
import com.anish.blooddonation.repository.BloodRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KafkaConsumerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    private BloodRequestRepository requestRepository;

    @Autowired
    private MatchingService matchingService;

    @KafkaListener(topics = "blood-requests", groupId = "pulsenode-group")
    public void consumeBloodRequestEvent(Long requestId) {
        log.info(String.format("#### -> Consumed message -> %s", requestId));
        
        Optional<BloodRequest> requestOpt = requestRepository.findById(requestId);
        if (requestOpt.isPresent()) {
            matchingService.processNewRequest(requestOpt.get());
            log.info("#### -> Matching process completed for request -> " + requestId);
        } else {
            log.warn("#### -> Blood request not found for ID -> " + requestId);
        }
    }
}
