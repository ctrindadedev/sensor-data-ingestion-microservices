package com.sensor_data_ingestion.operations_service.controller;

import com.sensor_data_ingestion.operations_service.controller.dto.ApiErrorResponse;
import com.sensor_data_ingestion.operations_service.controller.dto.LeituraAceitaResponse;
import com.sensor_data_ingestion.operations_service.controller.dto.LeituraRequest;
import com.sensor_data_ingestion.operations_service.domain.either.Either;
import com.sensor_data_ingestion.operations_service.domain.error.ErroDeIngestao;
import com.sensor_data_ingestion.operations_service.domain.model.LeituraDoSensor;
import com.sensor_data_ingestion.operations_service.service.IngestaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/leituras")
@RequiredArgsConstructor
public class LeituraController {

    private final IngestaoService ingestaoService;

    @PostMapping
    public ResponseEntity<?> receberLeitura(@RequestBody LeituraRequest request) {

        Either<ErroDeIngestao, LeituraDoSensor> resultado = ingestaoService.processar(
                request.sensorId(),
                request.valor(),
                request.unidade(),
                request.timestamp()
        );

        return resultado.fold(
                erro -> {
                    log.warn("Falha de negócio ao processar leitura: {}", erro);
                    ApiErrorResponse responseError = new ApiErrorResponse(
                            erro.name(),
                            "Leitura rejeitada pelas regras de integridade do domínio."
                    );
                    return ResponseEntity.unprocessableContent().body(responseError);
                },

                sucesso -> {
                    log.info("Leitura processada e enfileirada com sucesso!");
                    LeituraAceitaResponse responseOk = new LeituraAceitaResponse(
                            "Processamento assíncrono iniciado",
                            sucesso.sensorId().valor()
                    );
                    return ResponseEntity.accepted().body(responseOk);
                }
        );
    }
}