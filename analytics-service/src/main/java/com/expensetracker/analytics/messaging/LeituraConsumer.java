package com.expensetracker.analytics.messaging;

import com.expensetracker.analytics.messaging.dto.LeituraEvent;
import com.expensetracker.analytics.model.DashboardLeitura;
import com.expensetracker.analytics.repository.DashboardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LeituraConsumer {

    private final DashboardRepository repository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ANALYTICS)
    public void receberLeituraDoRabbitMQ(LeituraEvent evento) {
        log.info("Recebendo leitura do Sensor [{}] via RabbitMQ", evento.sensorId());

        try {
            DashboardLeitura leituraDoc = new DashboardLeitura(
                    evento.sensorId(),
                    evento.valor(),
                    evento.unidade(),
                    evento.timestamp()
            );

            repository.save(leituraDoc);
            log.info("Leitura salva no Dashboard com sucesso!");

        } catch (Exception e) {
            //Apenas para simular o "Tratamento"
            log.error("Erro crítico ao processar evento do sensor {}. Motivo: {}", evento.sensorId(), e.getMessage());

        }
    }
}