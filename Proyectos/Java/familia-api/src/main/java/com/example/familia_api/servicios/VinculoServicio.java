package com.example.familia_api.servicios;

import com.example.familia_api.modelos.Estudiante;
import com.example.familia_api.modelos.Familiar;
import com.example.familia_api.modelos.Vinculo;
import com.example.familia_api.modelos.dto.VinculoDTO;
import com.example.familia_api.modelos.mapas.IMapaVinculo;
import com.example.familia_api.repositorios.IEstudianteRepositorio;
import com.example.familia_api.repositorios.IFamiliarRepositorio;
import com.example.familia_api.repositorios.IVinculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VinculoServicio {

    @Autowired
    private IVinculoRepositorio vinculoRepositorio;

    @Autowired
    private IEstudianteRepositorio estudianteRepositorio;

    @Autowired
    private IFamiliarRepositorio familiarRepositorio;

    @Autowired
    private IMapaVinculo mapaVinculo;

    public VinculoDTO crearVinculo(VinculoDTO vinculoDTO) throws Exception {
        try {
            Estudiante estudiante = estudianteRepositorio.findById(vinculoDTO.getEstudiante().getId())
                    .orElseThrow(() -> new Exception("Estudiante no encontrado"));

            Familiar familiar = familiarRepositorio.findById(vinculoDTO.getFamiliar().getId())
                    .orElseThrow(() -> new Exception("Familiar no encontrado"));

            Vinculo vinculo = new Vinculo(estudiante, familiar, vinculoDTO.getParentesco());
            vinculoRepositorio.save(vinculo);

            return mapaVinculo.toDto(vinculo);
        } catch (Exception error) {
            throw new Exception("Error al crear el vínculo: " + error.getMessage());
        }
    }

    public List<VinculoDTO> listarVinculos() throws Exception {
        try {
            return mapaVinculo.toDtoList(vinculoRepositorio.findAll());
        } catch (Exception error) {
            throw new Exception("Error al listar los vínculos: " + error.getMessage());
        }
    }
}
