package com.sensor_data_ingestion.operations_service.messaging;


import com.sensor_data_ingestion.operations_service.domain.model.LeituraDoSensor;

public record LeituraEvent(
        String sensorId,
        double valor,
        String unidade,
        String timestamp
) {

    public static LeituraEvent aPartirDoDominio(LeituraDoSensor leitura) {
        return new LeituraEvent(
                leitura.sensorId().valor(),
                leitura.valor().valor(),
                leitura.unidade().name(),
                leitura.timestamp().toString()
        );
    }
}
