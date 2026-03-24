package com.sensor_data_ingestion.operations_service.domain.model;

import com.sensor_data_ingestion.operations_service.domain.valueObjects.SensorId;
import com.sensor_data_ingestion.operations_service.domain.valueObjects.Unidade;
import com.sensor_data_ingestion.operations_service.domain.valueObjects.ValorMedicao;

import java.time.Instant;

public record LeituraDoSensor(
        SensorId sensorId,
        ValorMedicao valor,
        Unidade unidade,
        Instant timestamp
) {}