package com.mvp.logservice.service.impl;

import com.google.gson.Gson;
import com.mvp.logservice.dto.input.LogInput;
import com.mvp.logservice.service.KafkaService;
import com.mvp.logservice.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {
    private final static String topic = "mvp-app";
    private final static String groupId = "mvpFinal";
    private final LogService logService;

    @KafkaListener(topics = topic, groupId = groupId)
    @Override
    public void listen(String message) {
        Gson g = new Gson();
        LogInput logInput = g.fromJson(message, LogInput.class);
        logService.create(logInput);
    }
}
