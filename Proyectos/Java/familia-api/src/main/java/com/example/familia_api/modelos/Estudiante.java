package com.example.familia_api.modelos;

import com.example.familia_api.ayudas.Rol;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Estudiantes")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Estudiante extends Usuario {

    private String programa;

    private Integer semestre;

    @Column(precision = 3, scale = 2)
    private BigDecimal promedio;

    public Estudiante() {
        // Constructor por defecto
    }

    // Constructor para crear/actualizar los detalles específicos de un Estudiante
    // Asume que el Usuario base ya existe y tiene el rol ESTUDIANTE
    public Estudiante(String programa, Integer semestre, BigDecimal promedio) {
        // No se llama a super() con los detalles del Usuario, ya que esta entidad representa la *extensión* de un Usuario
        this.programa = programa;
        this.semestre = semestre;
        this.promedio = promedio;
    }

    // Getters y Setters (ya presentes)
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
