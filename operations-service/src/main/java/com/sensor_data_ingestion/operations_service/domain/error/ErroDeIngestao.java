package com.sensor_data_ingestion.operations_service.domain.error;

public enum ErroDeIngestao {
    SENSOR_ID_INVALIDO,
    VALOR_FORA_DO_RANGE,
    UNIDADE_DESCONHECIDA,
    TIMESTAMP_INVALIDO
}