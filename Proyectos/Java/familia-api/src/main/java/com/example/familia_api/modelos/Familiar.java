package com.example.familia_api.modelos;

import com.example.familia_api.ayudas.Rol;
import jakarta.persistence.*;

@Entity
@Table(name = "Familiares")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Familiar extends Usuario {

    // Por ahora, esta tabla no tiene campos adicionales específicos de Familiar
    // Si se añadieran, se incluirían en un constructor similar al de Estudiante

    public Familiar() {
        // Constructor por defecto
    }

    // No se necesita un constructor específico si no hay campos adicionales más allá de los heredados de Usuario
}
