package com.expensetracker.analytics.repository;


import com.expensetracker.analytics.model.DashboardLeitura;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRepository extends MongoRepository<DashboardLeitura, String> {

    List<DashboardLeitura> findBySensorIdOrderByTimestampRecebimentoDesc(String sensorId);
}