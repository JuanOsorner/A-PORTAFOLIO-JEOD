package com.example.familia_api.modelos;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vinculo_id", nullable = false)
    private Vinculo vinculo;

    @Column(name = "fecha_consulta", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaConsulta;

    @Lob
    private String observaciones;
    // Esto es para ejecutar la fecha de manera automatica
    @PrePersist
    protected void onCreate() {
        this.fechaConsulta = LocalDateTime.now();
    }

    // Constructores, Getters y Setters

    public Consulta() {
    }

    public Consulta(Vinculo vinculo, String observaciones) {
        this.vinculo = vinculo;
        this.observaciones = observaciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vinculo getVinculo() {
        return vinculo;
    }

    public void setVinculo(Vinculo vinculo) {
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
