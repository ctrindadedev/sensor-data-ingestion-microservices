package com.sensor_data_ingestion.operations_service.controller.dto;

public record LeituraRequest(
            String sensorId,
            double valor,
            String unidade,
            long timestamp
    ) { }
