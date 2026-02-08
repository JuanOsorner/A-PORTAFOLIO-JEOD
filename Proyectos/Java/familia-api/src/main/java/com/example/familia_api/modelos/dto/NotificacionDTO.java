package com.example.familia_api.modelos.dto;

import java.time.LocalDateTime;

public class NotificacionDTO {

    private Integer id;
    private Integer familiarId;
    private String mensaje;
    private boolean leida;
    private LocalDateTime fechaCreacion;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFamiliarId() {
        return familiarId;
    }

    public void setFamiliarId(Integer familiarId) {
        this.familiarId = familiarId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
