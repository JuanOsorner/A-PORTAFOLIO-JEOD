package com.example.familia_api.servicios;

import com.example.familia_api.modelos.Familiar;
import com.example.familia_api.modelos.Notificacion;
import com.example.familia_api.modelos.dto.NotificacionDTO;
import com.example.familia_api.modelos.mapas.IMapaNotificacion;
import com.example.familia_api.repositorios.IFamiliarRepositorio;
import com.example.familia_api.repositorios.INotificacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionServicio {

    @Autowired
    private INotificacionRepositorio notificacionRepositorio;

    @Autowired
    private IFamiliarRepositorio familiarRepositorio;

    @Autowired
    private IMapaNotificacion mapaNotificacion;

    public NotificacionDTO crearNotificacion(Integer familiarId, String mensaje) throws Exception {
        try {
            Familiar familiar = familiarRepositorio.findById(familiarId)
                    .orElseThrow(() -> new Exception("Familiar no encontrado"));

            Notificacion notificacion = new Notificacion(familiar, mensaje);
            notificacionRepositorio.save(notificacion);

            return mapaNotificacion.toDto(notificacion);
        } catch (Exception error) {
            throw new Exception("Error al crear la notificación: " + error.getMessage());
        }
    }

    public List<NotificacionDTO> buscarNotificacionesPorFamiliar(Integer familiarId) throws Exception {
        try {
            return mapaNotificacion.toDtoList(notificacionRepositorio.findByFamiliarId(familiarId));
        } catch (Exception error) {
            throw new Exception("Error al buscar las notificaciones: " + error.getMessage());
        }
    }
}
