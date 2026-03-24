package com.sensor_data_ingestion.operations_service.domain.valueObjects;

import com.sensor_data_ingestion.operations_service.domain.either.Either;
import com.sensor_data_ingestion.operations_service.domain.error.ErroDeIngestao;

public enum Unidade {
    BAR, PSI, CELSIUS, KPA;

    public static Either<ErroDeIngestao, Unidade> of(String valor) {
        try {
            return Either.right(Unidade.valueOf(valor.toUpperCase()));
        } catch (IllegalArgumentException ignored) {
            return Either.left(ErroDeIngestao.UNIDADE_DESCONHECIDA);
        }
    }
}