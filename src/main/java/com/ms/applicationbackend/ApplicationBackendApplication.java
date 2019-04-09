package com.ms.applicationbackend;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRabbit
public class ApplicationBackendApplication {

	@Autowired
	private ApplicationConfigReader applicationConfig;

	public ApplicationConfigReader getApplicationConfig() {
		return applicationConfig;
	}

	public void setApplicationConfig(ApplicationConfigReader applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationBackendApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationBackendApplication.class);
	}

	/* This bean is to read the properties file configs */
	@Bean
	public ApplicationConfigReader applicationConfig() {
		return new ApplicationConfigReader();
	}

	/* Creating a bean for the Message queue Exchange */
	@Bean
	public TopicExchange getKoExchange() {
		return new TopicExchange(getApplicationConfig().getKoExchangeName());
	}

	/* Creating a bean for the Message queue */
	@Bean
	public Queue getKoSalaryQueue() {
		return new Queue(getApplicationConfig().getKoSalaryQueueName());
	}

	/* Binding between Exchange and Queue using routing key */
	@Bean
	public Binding declareBindingKoSalary() {
		return BindingBuilder.bind(getKoSalaryQueue()).to(getKoExchange()).with(getApplicationConfig().getKoSalaryRoutingKey());
	}

}
