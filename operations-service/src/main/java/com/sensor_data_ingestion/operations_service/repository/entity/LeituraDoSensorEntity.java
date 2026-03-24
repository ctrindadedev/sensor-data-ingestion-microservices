package com.sensor_data_ingestion.operations_service.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "leituras_sensor")
public class LeituraDoSensorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sensor_id")
    private String sensorId;

    private double valor;

    private String unidade;

    @Column(name = "instante_processamento")
    private Instant timestamp;
}