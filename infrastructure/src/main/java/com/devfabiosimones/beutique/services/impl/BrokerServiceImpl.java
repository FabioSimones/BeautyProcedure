package com.devfabiosimones.beutique.services.impl;

import com.devfabiosimones.beutique.configurations.RabbitMQTopicConfig;
import com.devfabiosimones.beutique.services.BrokerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class BrokerServiceImpl implements BrokerService {

    private final ObjectMapper objectMapper;

    private final RabbitTemplate rabbitTemplate;

    private final RabbitMQTopicConfig rabbitMQTopicConfig;

    public BrokerServiceImpl(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate, RabbitMQTopicConfig rabbitMQTopicConfig) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQTopicConfig = rabbitMQTopicConfig;
    }

    @Override
    public void send(String type, Object data) {
        String routingKey = type + ".#";
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            rabbitTemplate.convertAndSend(rabbitMQTopicConfig.exchangeName, routingKey, jsonData, message -> {
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            });
        } catch (Exception e) {
            throw new RuntimeException("Error serializing message " + e.getMessage());
        }
    }
}
