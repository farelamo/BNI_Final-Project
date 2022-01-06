package com.mvp.bni.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {
    private static String topic = "mvp-app";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message){
        kafkaTemplate.send(topic, message);
    }
}