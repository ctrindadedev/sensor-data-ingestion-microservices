package com.sensor_data_ingestion.operations_service.repository;

import com.sensor_data_ingestion.operations_service.repository.entity.LeituraDoSensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeituraRepository extends JpaRepository<LeituraDoSensorEntity, Long> {
}
