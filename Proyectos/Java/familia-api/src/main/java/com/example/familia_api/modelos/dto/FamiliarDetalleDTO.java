package com.example.familia_api.modelos.dto;

public class FamiliarDetalleDTO {
    private Integer usuarioId; // El ID del Usuario base al que se asocia
    // Por ahora, no hay campos adicionales específicos para Familiar

    // Getters y Setters
    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }
}
