package com.example.familia_api.modelos.dto;

import java.math.BigDecimal;

public class EstudianteDetalleDTO {
    private Integer usuarioId; // El ID del Usuario base al que se asocia
    private String programa;
    private Integer semestre;
    private BigDecimal promedio;

    // Getters y Setters
    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }
    public String getPrograma() { return programa; }
    public void setPrograma(String programa) { this.programa = programa; }
    public Integer getSemestre() { return semestre; }
    public void setSemestre(Integer semestre) { this.semestre = semestre; }
    public BigDecimal getPromedio() { return promedio; }
    public void setPromedio(BigDecimal promedio) { this.promedio = promedio; }
}
