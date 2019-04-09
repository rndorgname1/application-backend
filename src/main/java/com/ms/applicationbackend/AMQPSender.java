package com.ms.applicationbackend;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class AMQPSender {

    private final RabbitTemplate rabbitTemplate;

    public AMQPSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String topicExchangeName, String routingKey, String message) {
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey, message);
    }
}
