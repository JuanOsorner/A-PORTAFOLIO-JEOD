package com.example.familia_api.modelos.mapas;

import com.example.familia_api.modelos.Notificacion;
import com.example.familia_api.modelos.dto.NotificacionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaNotificacion {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "familiar.id", target = "familiarId")
    @Mapping(source = "mensaje", target = "mensaje")
    @Mapping(source = "leida", target = "leida")
    @Mapping(source = "fechaCreacion", target = "fechaCreacion")
    NotificacionDTO toDto(Notificacion notificacion);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "familiar", ignore = true) // Se manejará en el servicio
    Notificacion toEntity(NotificacionDTO dto);

    List<NotificacionDTO> toDtoList(List<Notificacion> notificaciones);
}
