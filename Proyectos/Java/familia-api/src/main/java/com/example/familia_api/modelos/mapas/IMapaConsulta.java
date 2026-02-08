package com.example.familia_api.modelos.mapas;

import com.example.familia_api.modelos.Consulta;
import com.example.familia_api.modelos.dto.ConsultaCompletaDTO;
import com.example.familia_api.modelos.dto.ConsultaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = IMapaVinculo.class)
public interface IMapaConsulta {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "vinculo.id", target = "vinculoId")
    @Mapping(source = "fechaConsulta", target = "fechaConsulta")
    @Mapping(source = "observaciones", target = "observaciones")
    ConsultaDTO toDto(Consulta consulta);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vinculo", ignore = true) // Se manejará en el servicio
    Consulta toEntity(ConsultaDTO dto);

    List<ConsultaDTO> toDtoList(List<Consulta> consultas);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "vinculo", target = "vinculo")
    @Mapping(source = "fechaConsulta", target = "fechaConsulta")
    @Mapping(source = "observaciones", target = "observaciones")
    ConsultaCompletaDTO toCompletaDto(Consulta consulta);

    List<ConsultaCompletaDTO> toCompletaDtoList(List<Consulta> consultas);
}
