package com.sensor_data_ingestion.operations_service.domain.valueObjects;

import com.sensor_data_ingestion.operations_service.domain.either.Either;
import com.sensor_data_ingestion.operations_service.domain.error.ErroDeIngestao;

public record SensorId(String valor) {
    public static Either<ErroDeIngestao, SensorId> of(String valor) {
        if (valor == null || valor.isBlank()) {
            return Either.left(ErroDeIngestao.SENSOR_ID_INVALIDO);
        }

        return Either.right(new SensorId(valor));
    }
}