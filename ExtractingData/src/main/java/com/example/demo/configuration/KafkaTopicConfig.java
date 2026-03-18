package com.example.demo.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class KafkaTopicConfig {

    @Value(value = "${spring.rabbitmq.host}")
    private String rabbitHost;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("source_exchange");
    }

    @Bean
    public Queue queue() {
        return new Queue("source_queue", true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("source_topic");
    }
}