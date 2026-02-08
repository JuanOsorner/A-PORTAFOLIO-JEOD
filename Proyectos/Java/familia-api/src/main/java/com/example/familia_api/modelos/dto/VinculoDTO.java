package com.example.familia_api.modelos.dto;

public class VinculoDTO {

    private Integer id;
    private EstudianteDTO estudiante;
    private FamiliarDTO familiar;
    private String parentesco;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstudianteDTO getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteDTO estudiante) {
        this.estudiante = estudiante;
    }

    public FamiliarDTO getFamiliar() {
        return familiar;
    }

    public void setFamiliar(FamiliarDTO familiar) {
        this.familiar = familiar;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
}
