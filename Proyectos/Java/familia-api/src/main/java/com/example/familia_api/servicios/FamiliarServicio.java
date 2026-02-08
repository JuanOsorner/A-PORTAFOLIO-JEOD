package com.example.familia_api.servicios;

import com.example.familia_api.ayudas.Rol;
import com.example.familia_api.modelos.Familiar;
import com.example.familia_api.modelos.Usuario;
import com.example.familia_api.modelos.dto.FamiliarDTO;
import com.example.familia_api.modelos.dto.FamiliarDetalleDTO;
import com.example.familia_api.modelos.mapas.IMapaFamiliar;
import com.example.familia_api.repositorios.IFamiliarRepositorio;
import com.example.familia_api.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamiliarServicio {

    @Autowired
    private IFamiliarRepositorio familiarRepositorio;
    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;
    @Autowired
    private IMapaFamiliar mapaFamiliar;

    // Método para crear o actualizar los detalles de un Familiar
    public FamiliarDTO guardarFamiliarDetalle(FamiliarDetalleDTO detalleDTO) throws Exception {
        try {
            // 1. Buscar el Usuario base
            Usuario usuarioBase = usuarioRepositorio.findById(detalleDTO.getUsuarioId())
                    .orElseThrow(() -> new Exception("Usuario base no encontrado para el ID: " + detalleDTO.getUsuarioId()));

            // 2. Verificar que el rol del Usuario base sea FAMILIAR
            if (usuarioBase.getRol() != Rol.FAMILIAR) {
                throw new Exception("El usuario con ID " + detalleDTO.getUsuarioId() + " no tiene el rol de FAMILIAR.");
            }

            // 3. Intentar encontrar si ya existe una entrada de Familiar para este usuario
            Optional<Familiar> familiarExistente = familiarRepositorio.findById(detalleDTO.getUsuarioId());
            Familiar familiar;

            if (familiarExistente.isPresent()) {
                // Actualizar familiar existente (por ahora no hay campos específicos, pero se mantiene la estructura)
                familiar = familiarExistente.get();
                // Aquí irían actualizaciones de campos específicos de Familiar si los hubiera
            } else {
                // Crear nueva entrada de Familiar
                familiar = new Familiar(); // Usar constructor por defecto
                // Es crucial establecer el ID del Familiar para que coincida con el ID del Usuario base
                familiar.setId(detalleDTO.getUsuarioId());
                // Copiar propiedades del Usuario base al Familiar (ya que Familiar extiende Usuario)
                familiar.setNombre(usuarioBase.getNombre());
                familiar.setCorreo(usuarioBase.getCorreo());
                familiar.setContra(usuarioBase.getContra()); // La contraseña ya está hasheada en usuarioBase
                familiar.setEstado(usuarioBase.getEstado());
                familiar.setFechaCreacion(usuarioBase.getFechaCreacion());
                familiar.setRol(usuarioBase.getRol());
            }

            Familiar savedFamiliar = familiarRepositorio.save(familiar);
            return mapaFamiliar.toDto(savedFamiliar);

        } catch (Exception error) {
            throw new Exception("Error al guardar los detalles del familiar: " + error.getMessage());
        }
    }

    public FamiliarDTO buscarFamiliarPorId(Integer id) throws Exception {
        try {
            Optional<Familiar> familiarEncontrado = familiarRepositorio.findById(id);
            if (familiarEncontrado.isPresent()) {
                return mapaFamiliar.toDto(familiarEncontrado.get());
            } else {
                throw new Exception("Familiar no encontrado");
            }
        } catch (Exception error) {
            throw new Exception("Error al buscar el familiar: " + error.getMessage());
        }
    }

    public List<FamiliarDTO> buscarTodosLosFamiliares() throws Exception {
        try {
            return mapaFamiliar.toDtoList(familiarRepositorio.findAll());
        } catch (Exception error) {
            throw new Exception("Error al buscar todos los familiares: " + error.getMessage());
        }
    }
}
