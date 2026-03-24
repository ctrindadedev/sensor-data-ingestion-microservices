package com.sensor_data_ingestion.operations_service.domain.valueObjects;

import com.sensor_data_ingestion.operations_service.domain.either.Either;
import com.sensor_data_ingestion.operations_service.domain.error.ErroDeIngestao;

public record ValorMedicao(double valor) {

    private static final double MINIMO = 0.0;
    private static final double MAXIMO = 1000.0;

    public static Either<ErroDeIngestao, ValorMedicao> of(double valor) {
        if (valor < MINIMO || valor > MAXIMO) {
            return Either.left(ErroDeIngestao.VALOR_FORA_DO_RANGE);
        }
        return Either.right(new ValorMedicao(valor));
    }
}