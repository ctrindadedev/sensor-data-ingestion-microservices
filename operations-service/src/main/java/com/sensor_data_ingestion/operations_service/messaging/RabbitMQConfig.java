package com.sensor_data_ingestion.operations_service.messaging;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "telemetria.exchange";
    public static final String QUEUE_ANALYTICS = "analytics.leitura.queue";
    public static final String ROUTING_KEY = "sensor.leitura.criada";

    @Bean
    public DirectExchange telemetriaExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue analyticsQueue() {
        return new Queue(QUEUE_ANALYTICS, true);
    }

    @Bean
    public Binding binding(Queue analyticsQueue, DirectExchange telemetriaExchange) {
        return BindingBuilder.bind(analyticsQueue).to(telemetriaExchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}