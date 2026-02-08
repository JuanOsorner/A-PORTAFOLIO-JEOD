package com.example.familia_api.modelos.dto;

public class AccesoHoraDTO {
    private Integer hora;
    private Long numeroDeAccesos;

    public AccesoHoraDTO(Integer hora, Long numeroDeAccesos) {
        this.hora = hora;
        this.numeroDeAccesos = numeroDeAccesos;
    }

    // Getters y Setters
    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    public Long getNumeroDeAccesos() {
        return numeroDeAccesos;
    }

    public void setNumeroDeAccesos(Long numeroDeAccesos) {
        this.numeroDeAccesos = numeroDeAccesos;
    }
}
