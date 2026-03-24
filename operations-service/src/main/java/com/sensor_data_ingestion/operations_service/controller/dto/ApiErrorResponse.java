package com.sensor_data_ingestion.operations_service.controller.dto;

public record ApiErrorResponse(
            String codigo,
            String mensagem
    ) { }

