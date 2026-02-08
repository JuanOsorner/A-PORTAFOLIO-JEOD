package com.example.familia_api.modelos.dto;

import java.time.LocalDateTime;

public class ConsultaDTO {

    private Integer id;
    private Integer vinculoId;
    private LocalDateTime fechaConsulta;
    private String observaciones;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVinculoId() {
        return vinculoId;
    }

    public void setVinculoId(Integer vinculoId) {
        this.vinculoId = vinculoId;
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
