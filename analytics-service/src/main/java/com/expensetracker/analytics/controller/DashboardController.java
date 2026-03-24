package com.expensetracker.analytics.controller;

import com.expensetracker.analytics.model.DashboardLeitura;
import com.expensetracker.analytics.repository.DashboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard/leituras")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardRepository repository;

    @GetMapping("/{sensorId}")
    public ResponseEntity<List<DashboardLeitura>> buscarHistoricoSensor(@PathVariable String sensorId) {
        List<DashboardLeitura> historico = repository.findBySensorIdOrderByTimestampRecebimentoDesc(sensorId);
        return ResponseEntity.ok(historico);
    }

    @GetMapping
    public ResponseEntity<List<DashboardLeitura>> buscarTudo() {
        return ResponseEntity.ok(repository.findAll());
    }
}