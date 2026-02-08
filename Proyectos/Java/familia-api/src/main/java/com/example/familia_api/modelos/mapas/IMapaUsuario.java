package com.example.familia_api.modelos.mapas;

import com.example.familia_api.modelos.Usuario;
import com.example.familia_api.modelos.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMapaUsuario {

    // Traducción a DTO
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "correo", source = "correo")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "rol", source = "rol")
    UsuarioDTO toDto(Usuario usuario);
    // Traducción a MODELO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contra", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "rol", source = "rol")
    Usuario toEntity(UsuarioDTO dto);

    List<UsuarioDTO> toDtoList(List<Usuario> usuarios);
}
