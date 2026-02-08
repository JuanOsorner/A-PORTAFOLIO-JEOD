package com.example.familia_api.repositorios;

import com.example.familia_api.modelos.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IEstudianteRepositorio extends JpaRepository<Estudiante, Integer> {
    // Se añade un método derivado de consulta para buscar Estudiantes por su promedio.
    List<Estudiante> findByPromedio(BigDecimal promedio);
}
