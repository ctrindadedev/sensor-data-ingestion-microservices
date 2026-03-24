package com.expensetracker.analytics.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "leituras_dashboard")
public class DashboardLeitura {

    @Id
    private String id;
    private String sensorId;
    private double valor;
    private String unidade;
    private String timestampRecebimento;

    public DashboardLeitura(String sensorId, double valor, String unidade, String timestampRecebimento) {
            this.sensorId = sensorId;
            this.valor = valor;
            this.unidade = unidade;
            this.timestampRecebimento = timestampRecebimento;
        }
    }
