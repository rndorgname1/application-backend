package com.ms.applicationbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfigReader {

    @Value("${ko.exchange.name}")
    private String koExchangeName;

    @Value("${ko.salary.queue.name}")
    private String koSalaryQueueName;

    @Value("${ko.salary.routing.key}")
    private String koSalaryRoutingKey;

    public String getKoExchangeName() {
        return koExchangeName;
    }

    public String getKoSalaryQueueName() { return koSalaryQueueName; }

    public String getKoSalaryRoutingKey() { return koSalaryRoutingKey; }
}
