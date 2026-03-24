package com.sensor_data_ingestion.operations_service.service;

import com.sensor_data_ingestion.operations_service.domain.either.Either;
import com.sensor_data_ingestion.operations_service.domain.error.ErroDeIngestao;
import com.sensor_data_ingestion.operations_service.domain.model.LeituraDoSensor;
import com.sensor_data_ingestion.operations_service.messaging.LeituraPublisher;
import com.sensor_data_ingestion.operations_service.repository.LeituraRepository;
import com.sensor_data_ingestion.operations_service.repository.entity.LeituraDoSensorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public  class IngestaoServiceTest {

        @Mock
        private LeituraRepository repository;
        @Mock
        private LeituraPublisher publisher;

        @InjectMocks
        private IngestaoService service;

        @Test
        void naoDeveSalvarNoBancoNemPublicarEventoQuandoDominioForInvalido() {

            // Act
            Either<ErroDeIngestao, LeituraDoSensor> resultado =
                    service.processar("SENSOR-1", -50.0, "PSI", System.currentTimeMillis());

            // Assert
            assertInstanceOf(Either.Left.class, resultado);

            verify(repository, never()).save(any());
            verify(publisher, never()).enviarEventoTelemetria(any());
        }

        @Test
        void deveSalvarEPublicarQuandoDominioForValido() {

            Either<ErroDeIngestao, LeituraDoSensor> resultado =
                    service.processar("SENSOR-1", 100.0, "PSI", System.currentTimeMillis());

            assertInstanceOf(Either.Right.class, resultado);

            verify(repository, times(1)).save(any(LeituraDoSensorEntity.class));
            verify(publisher, times(1)).enviarEventoTelemetria(any(LeituraDoSensor.class));
        }
    }
