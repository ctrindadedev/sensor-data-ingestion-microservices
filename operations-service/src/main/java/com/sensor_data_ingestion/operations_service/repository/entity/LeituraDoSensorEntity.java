package com.sensor_data_ingestion.operations_service.repository.entity;

import com.sensor_data_ingestion.operations_service.domain.model.LeituraDoSensor;
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

    public static LeituraDoSensorEntity aPartirDoDominio(LeituraDoSensor dominio) {
        LeituraDoSensorEntity entity = new LeituraDoSensorEntity();

        entity.sensorId = dominio.sensorId().valor();
        entity.valor = dominio.valor().valor();
        entity.unidade = dominio.unidade().name();
        entity.timestamp = dominio.timestamp();
        return entity;
    }
}