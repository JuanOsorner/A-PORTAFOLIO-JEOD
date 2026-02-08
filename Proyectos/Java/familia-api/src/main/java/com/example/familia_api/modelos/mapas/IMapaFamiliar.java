package com.example.familia_api.modelos.mapas;

import com.example.familia_api.modelos.Familiar;
import com.example.familia_api.modelos.dto.FamiliarDTO;
import com.example.familia_api.modelos.dto.FamiliarDetalleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMapaFamiliar {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "rol", target = "rol")
    FamiliarDTO toDto(Familiar familiar);

    // Mapeo para FamiliarDetalleDTO a Familiar (para actualizar detalles)
    // Aquí no se mapean propiedades de Usuario, solo las específicas de Familiar (si las hubiera)
    @Mapping(target = "id", source = "usuarioId") // El ID del Familiar es el usuarioId
    @Mapping(target = "nombre", ignore = true)
    @Mapping(target = "correo", ignore = true)
    @Mapping(target = "contra", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "rol", ignore = true)
    Familiar toEntity(FamiliarDetalleDTO dto);

    List<FamiliarDTO> toDtoList(List<Familiar> familiares);
}
