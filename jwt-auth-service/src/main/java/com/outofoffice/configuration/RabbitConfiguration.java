package com.outofoffice.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    public static final String QUEUE = "request_notification_queue";
    public static final String QUEUE2 = "request_employee_queue";
    public static final String QUEUE3 = "employee_notification_queue";
    public static final String QUEUE4 = "employee_auth_queue";
    public static final String EXCHANGE = "request_notification_exchange";
    public static final String EXCHANGE2 = "request_employee_exchange";
    public static final String ROUTING_KEY = "request_notification_routingKey";
    public static final String ROUTING_KEY2 = "request_employee_routingKey";
    public static final String ROUTING_KEY3 = "employee_notification_routingKey";
    public static final String ROUTING_KEY4 = "employee_auth_routingKey";

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE);
    }
    @Bean
    public Queue queue2() {
        return new Queue(QUEUE2);
    }
    @Bean
    public Queue queue3() {
        return new Queue(QUEUE3);
    }
    @Bean
    public Queue queue4() {
        return new Queue(QUEUE4);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding1(TopicExchange exchange) {
        return BindingBuilder.bind(queue1()).to(exchange).with(ROUTING_KEY);
    }
    @Bean
    public Binding binding2(TopicExchange exchange) {
        return BindingBuilder.bind(queue2()).to(exchange).with(ROUTING_KEY2);
    }
    @Bean
    public Binding binding3(TopicExchange exchange) {
        return BindingBuilder.bind(queue3()).to(exchange).with(ROUTING_KEY3);
    }
    @Bean
    public Binding binding4(TopicExchange exchange) {
        return BindingBuilder.bind(queue4()).to(exchange).with(ROUTING_KEY4);
    }



    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
