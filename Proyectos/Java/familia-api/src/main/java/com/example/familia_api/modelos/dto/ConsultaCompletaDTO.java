package com.example.familia_api.modelos.dto;

import java.time.LocalDateTime;

public class ConsultaCompletaDTO {

    private Integer id;
    private VinculoDTO vinculo;
    private LocalDateTime fechaConsulta;
    private String observaciones;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VinculoDTO getVinculo() {
        return vinculo;
    }

    public void setVinculo(VinculoDTO vinculo) {
        this.vinculo = vinculo;
    }

    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDateTime fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
