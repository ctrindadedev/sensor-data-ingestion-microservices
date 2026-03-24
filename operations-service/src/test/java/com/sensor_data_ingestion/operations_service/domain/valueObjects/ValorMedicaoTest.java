package com.sensor_data_ingestion.operations_service.domain.valueObjects;

import com.sensor_data_ingestion.operations_service.domain.either.Either;
import com.sensor_data_ingestion.operations_service.domain.error.ErroDeIngestao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValorMedicaoTest {
        @Test
        void deveRetornarErroQuandoValorForAbaixoDeZero() {
            Either<ErroDeIngestao, ValorMedicao> resultado = ValorMedicao.of(-10.5);

            assertInstanceOf(Either.Left.class, resultado);
            resultado.fold(
                    erro -> {
                        assertEquals(ErroDeIngestao.VALOR_FORA_DO_RANGE, erro);
                        return null;
                    },
                    sucesso -> fail("Não deveria ter sucesso")
            );
        }
    }


