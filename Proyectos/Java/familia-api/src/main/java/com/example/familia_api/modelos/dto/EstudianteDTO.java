package com.example.familia_api.modelos.dto;

import java.math.BigDecimal;

public class EstudianteDTO extends UsuarioDTO {

    private String programa;
    private Integer semestre;
    private BigDecimal promedio;

    // Getters y Setters

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public BigDecimal getPromedio() {
        return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
        this.promedio = promedio;
    }
}
