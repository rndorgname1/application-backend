package com.ms.applicationbackend;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class PersonController {

    private final RabbitTemplate rabbitTemplate;
    private ApplicationConfigReader applicationConfig;

    @Autowired
    public void setApplicationConfig(ApplicationConfigReader applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Autowired
    public PersonController(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @PostMapping("/person")
    Person newPerson(@RequestBody Person newPerson) {

        System.out.println(newPerson.getFirstName() + " " + newPerson.getLastName());

        AMQPSender sender = new AMQPSender(rabbitTemplate);
        sender.sendMessage(applicationConfig.getKoExchangeName(), applicationConfig.getKoSalaryRoutingKey(), "Got a new person, please prepare heavy calculations.");
        return newPerson;
    }
}
