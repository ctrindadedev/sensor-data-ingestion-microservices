package com.sensor_data_ingestion.operations_service.controller;

import com.sensor_data_ingestion.operations_service.controller.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiErrorResponse> handleDatabaseErrors(DataAccessException ex) {
        log.error("Erro critico de banco de dados: ", ex);

        ApiErrorResponse response = new ApiErrorResponse(
                "ERRO_INFRAESTRUTURA",
                "O serviço está temporariamente indisponível devido a falhas de persistência."
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericErrors(Exception ex) {
        log.error("Erro interno inesperado: ", ex);

        ApiErrorResponse response = new ApiErrorResponse(
                "ERRO_INTERNO_SERVIDOR",
                "Ocorreu um erro inesperado no processamento da requisição."
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}