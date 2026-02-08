package com.example.familia_api.repositorios;

import com.example.familia_api.modelos.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotificacionRepositorio extends JpaRepository<Notificacion, Integer> {

    // Se añade un método derivado de consulta para buscar Notificaciones por el ID del familiar.
    List<Notificacion> findByFamiliarId(Integer familiarId);
}
