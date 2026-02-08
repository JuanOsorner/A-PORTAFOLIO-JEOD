package com.example.familia_api.servicios;

import com.example.familia_api.ayudas.Rol;
import com.example.familia_api.modelos.Estudiante;
import com.example.familia_api.modelos.Usuario;
import com.example.familia_api.modelos.dto.EstudianteDTO;
import com.example.familia_api.modelos.dto.EstudianteDetalleDTO;
import com.example.familia_api.modelos.mapas.IMapaEstudiante;
import com.example.familia_api.repositorios.IEstudianteRepositorio;
import com.example.familia_api.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServicio {

    @Autowired
    private IEstudianteRepositorio estudianteRepositorio;
    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;
    @Autowired
    private IMapaEstudiante mapaEstudiante;

    // Método para crear o actualizar los detalles de un Estudiante
    public EstudianteDTO guardarEstudianteDetalle(EstudianteDetalleDTO detalleDTO) throws Exception {
        try {
            // 1. Buscar el Usuario base
            Usuario usuarioBase = usuarioRepositorio.findById(detalleDTO.getUsuarioId())
                    .orElseThrow(() -> new Exception("Usuario base no encontrado para el ID: " + detalleDTO.getUsuarioId()));

            // 2. Verificar que el rol del Usuario base sea ESTUDIANTE
            if (usuarioBase.getRol() != Rol.ESTUDIANTE) {
                throw new Exception("El usuario con ID " + detalleDTO.getUsuarioId() + " no tiene el rol de ESTUDIANTE.");
            }

            // 3. Intentar encontrar si ya existe una entrada de Estudiante para este usuario
            Optional<Estudiante> estudianteExistente = estudianteRepositorio.findById(detalleDTO.getUsuarioId());
            Estudiante estudiante;

            if (estudianteExistente.isPresent()) {
                // Actualizar estudiante existente
                estudiante = estudianteExistente.get();
                estudiante.setPrograma(detalleDTO.getPrograma());
                estudiante.setSemestre(detalleDTO.getSemestre());
                estudiante.setPromedio(detalleDTO.getPromedio());
            } else {
                // Crear nueva entrada de Estudiante
                estudiante = new Estudiante(detalleDTO.getPrograma(), detalleDTO.getSemestre(), detalleDTO.getPromedio());
                // Es crucial establecer el ID del Estudiante para que coincida con el ID del Usuario base
                estudiante.setId(detalleDTO.getUsuarioId());
                // Copiar propiedades del Usuario base al Estudiante (ya que Estudiante extiende Usuario)
                estudiante.setNombre(usuarioBase.getNombre());
                estudiante.setCorreo(usuarioBase.getCorreo());
                estudiante.setContra(usuarioBase.getContra()); // La contraseña ya está hasheada en usuarioBase
                estudiante.setEstado(usuarioBase.getEstado());
                estudiante.setFechaCreacion(usuarioBase.getFechaCreacion());
                estudiante.setRol(usuarioBase.getRol());
            }

            Estudiante savedEstudiante = estudianteRepositorio.save(estudiante);
            return mapaEstudiante.toDto(savedEstudiante);

        } catch (Exception error) {
            throw new Exception("Error al guardar los detalles del estudiante: " + error.getMessage());
        }
    }

    public EstudianteDTO buscarEstudiantePorId(Integer id) throws Exception {
        try {
            Optional<Estudiante> estudianteEncontrado = estudianteRepositorio.findById(id);
            if (estudianteEncontrado.isPresent()) {
                return mapaEstudiante.toDto(estudianteEncontrado.get());
            } else {
                throw new Exception("Estudiante no encontrado");
            }
        } catch (Exception error) {
            throw new Exception("Error al buscar el estudiante: " + error.getMessage());
        }
    }

    public List<EstudianteDTO> buscarTodosLosEstudiantes() throws Exception {
        try {
            return mapaEstudiante.toDtoList(estudianteRepositorio.findAll());
        } catch (Exception error) {
            throw new Exception("Error al buscar todos los estudiantes: " + error.getMessage());
        }
    }
}
