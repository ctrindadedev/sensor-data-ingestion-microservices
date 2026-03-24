package com.sensor_data_ingestion.operations_service.domain.model;

import com.sensor_data_ingestion.operations_service.domain.either.Either;
import com.sensor_data_ingestion.operations_service.domain.error.ErroDeIngestao;
import com.sensor_data_ingestion.operations_service.domain.valueObjects.SensorId;
import com.sensor_data_ingestion.operations_service.domain.valueObjects.Unidade;
import com.sensor_data_ingestion.operations_service.domain.valueObjects.ValorMedicao;

import java.time.Instant;

public record LeituraDoSensor(
        SensorId sensorId,
        ValorMedicao valor,
        Unidade unidade,
        Instant timestamp
) {
    public static Either<ErroDeIngestao, LeituraDoSensor> criar(
            String rawSensorId, double rawValor, String rawUnidade, long rawTimeStamp) {

        return SensorId.of(rawSensorId)
                .flatMap(sensorId -> ValorMedicao.of(rawValor)
                        .flatMap(valor -> Unidade.of(rawUnidade)
                                .map(unidade ->
                                        new LeituraDoSensor(sensorId, valor, unidade, Instant.ofEpochMilli(rawTimeStamp))
                                )));
    }


}