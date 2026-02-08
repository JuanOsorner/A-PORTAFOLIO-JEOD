package com.example.familia_api.modelos.mapas;

import com.example.familia_api.modelos.Estudiante;
import com.example.familia_api.modelos.dto.EstudianteDTO;
import com.example.familia_api.modelos.dto.EstudianteDetalleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMapaEstudiante {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "rol", target = "rol")
    @Mapping(source = "programa", target = "programa")
    @Mapping(source = "semestre", target = "semestre")
    @Mapping(source = "promedio", target = "promedio")
    EstudianteDTO toDto(Estudiante estudiante);

    // Mapeo para EstudianteDetalleDTO a Estudiante (para actualizar detalles)
    // Aquí no se mapean propiedades de Usuario, solo las específicas de Estudiante
    @Mapping(target = "id", source = "usuarioId") // El ID del Estudiante es el usuarioId
    @Mapping(target = "nombre", ignore = true) // No se actualiza el nombre desde aquí
    @Mapping(target = "correo", ignore = true) // No se actualiza el correo desde aquí
    @Mapping(target = "contra", ignore = true) // No se actualiza la contraseña desde aquí
    @Mapping(target = "fechaCreacion", ignore = true) // No se actualiza la fecha de creación desde aquí
    @Mapping(target = "estado", ignore = true) // No se actualiza el estado desde aquí
    @Mapping(target = "rol", ignore = true) // El rol ya está en el Usuario base, no se actualiza desde aquí
    Estudiante toEntity(EstudianteDetalleDTO dto);

    List<EstudianteDTO> toDtoList(List<Estudiante> estudiantes);
}
