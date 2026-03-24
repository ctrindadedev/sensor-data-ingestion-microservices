package com.sensor_data_ingestion.operations_service.service;


import com.sensor_data_ingestion.operations_service.domain.either.Either;
import com.sensor_data_ingestion.operations_service.domain.error.ErroDeIngestao;
import com.sensor_data_ingestion.operations_service.domain.model.LeituraDoSensor;
import com.sensor_data_ingestion.operations_service.messaging.LeituraPublisher;
import com.sensor_data_ingestion.operations_service.repository.LeituraRepository;
import com.sensor_data_ingestion.operations_service.repository.entity.LeituraDoSensorEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class IngestaoService {

    private final LeituraRepository repository;
    private final LeituraPublisher rabbitPublisher;

    @Transactional
    public Either<ErroDeIngestao, LeituraDoSensor> processar(String rawSensorId, double rawValor, String rawUnidade, long rawTimeStamp) {

        return LeituraDoSensor.criar(rawSensorId, rawValor, rawUnidade, rawTimeStamp)
                .map(leituraValida -> {

                    LeituraDoSensorEntity leituraDoSensorToSave= LeituraDoSensorEntity.aPartirDoDominio(leituraValida);

                    repository.save(leituraDoSensorToSave);
                    rabbitPublisher.enviarEventoTelemetria(leituraValida);
                    return leituraValida;
                });
    }
}