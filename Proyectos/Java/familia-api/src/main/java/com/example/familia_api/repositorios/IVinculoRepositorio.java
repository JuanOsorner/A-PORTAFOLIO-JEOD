package com.example.familia_api.repositorios;

import com.example.familia_api.modelos.Vinculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVinculoRepositorio extends JpaRepository<Vinculo, Integer> {
    // Este repositorio extiende JpaRepository, lo que automáticamente proporciona
    // métodos CRUD básicos (save, findById, findAll, delete, etc.) para la entidad Vinculo.
    // No es necesario declararlos explícitamente aquí.
    // Por ahora, no se requieren consultas personalizadas para Vinculo, por lo que la interfaz está 'vacía'.
}
