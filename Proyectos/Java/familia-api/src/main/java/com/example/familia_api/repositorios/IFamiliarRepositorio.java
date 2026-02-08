package com.example.familia_api.repositorios;

import com.example.familia_api.modelos.Familiar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFamiliarRepositorio extends JpaRepository<Familiar, Integer> {
    // Recordemos que JpaRepository por defecto nos coloca los metodos basicos para ser usado
    // por los servicios
}
