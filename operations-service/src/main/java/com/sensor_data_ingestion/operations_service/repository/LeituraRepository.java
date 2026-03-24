package com.sensor_data_ingestion.operations_service.repository;

import com.sensor_data_ingestion.operations_service.domain.model.LeituraDoSensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeituraRepository extends JpaRepository<LeituraDoSensor, Long> {
}
