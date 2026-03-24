package com.sensor_data_ingestion.operations_service.messaging;

import com.sensor_data_ingestion.operations_service.domain.model.LeituraDoSensor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LeituraPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void enviarEventoTelemetria(LeituraDoSensor leituraValida) {

        LeituraEvent evento = LeituraEvent.aPartirDoDominio(leituraValida);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                evento
        );

        log.info(" Evento publicado no RabbitMQ para o sensor: {}", evento.sensorId());
    }
}