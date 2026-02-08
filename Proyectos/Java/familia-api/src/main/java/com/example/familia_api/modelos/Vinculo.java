package com.example.familia_api.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "Vinculos", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"estudiante_id", "familiar_id"})
})
public class Vinculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familiar_id", nullable = false)
    private Familiar familiar;

    private String parentesco;

    // Constructores, Getters y Setters

    public Vinculo() {
    }

    public Vinculo(Estudiante estudiante, Familiar familiar, String parentesco) {
        this.estudiante = estudiante;
        this.familiar = familiar;
        this.parentesco = parentesco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Familiar getFamiliar() {
        return familiar;
    }

    public void setFamiliar(Familiar familiar) {
        this.familiar = familiar;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
}
