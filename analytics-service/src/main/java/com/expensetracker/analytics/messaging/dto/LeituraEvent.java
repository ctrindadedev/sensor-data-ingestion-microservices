package com.expensetracker.analytics.messaging.dto;

public record LeituraEvent(
        String sensorId,
        double valor,
        String unidade,
        String timestamp
) {}
