package com.example.familia_api.modelos.dto;

public class AccesoEstudianteDTO {
    private Integer estudianteId;
    private Long numeroDeAccesos;

    public AccesoEstudianteDTO(Integer estudianteId, Long numeroDeAccesos) {
        this.estudianteId = estudianteId;
        this.numeroDeAccesos = numeroDeAccesos;
    }

    // Getters y Setters
    public Integer getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Integer estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Long getNumeroDeAccesos() {
        return numeroDeAccesos;
    }

    public void setNumeroDeAccesos(Long numeroDeAccesos) {
        this.numeroDeAccesos = numeroDeAccesos;
    }
}
