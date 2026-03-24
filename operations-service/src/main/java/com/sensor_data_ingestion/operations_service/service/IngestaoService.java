package com.sensor_data_ingestion.operations_service.service;


import com.sensor_data_ingestion.operations_service.domain.either.Either;
import com.sensor_data_ingestion.operations_service.domain.error.ErroDeIngestao;
import com.sensor_data_ingestion.operations_service.domain.model.LeituraDoSensor;
import com.sensor_data_ingestion.operations_service.domain.valueObjects.SensorId;
import com.sensor_data_ingestion.operations_service.domain.valueObjects.Unidade;
import com.sensor_data_ingestion.operations_service.domain.valueObjects.ValorMedicao;
import com.sensor_data_ingestion.operations_service.messaging.LeituraPublisher;
import com.sensor_data_ingestion.operations_service.repository.LeituraRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class IngestaoService {

    private final LeituraRepository repository;
    private final LeituraPublisher rabbitPublisher;

    @Transactional
    public Either<ErroDeIngestao, LeituraDoSensor> processar(String rawSensorId, double rawValor, String rawUnidade, long rawTimeStamp) {
        return SensorId.of(rawSensorId)
                .flatMap(sensorId -> ValorMedicao.of(rawValor)
                .flatMap(valor -> Unidade.of(rawUnidade)
                .map(unidade ->
                        new LeituraDoSensor(sensorId, valor, unidade, Instant.ofEpochMilli(rawTimeStamp)))
                        .map(leituraValida -> {
                            repository.save(leituraValida);
                            rabbitPublisher.enviarEventoTelemetria(leituraValida);
                    return leituraValida;
                });
        // 3. Se algo falhar (Left), o processo pula o bloco ".map" e devolve o erro pro Controller
    }

}