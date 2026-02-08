package com.example.familia_api.servicios;

import com.example.familia_api.modelos.Estudiante;
import com.example.familia_api.modelos.Familiar;
import com.example.familia_api.modelos.Usuario;
import com.example.familia_api.modelos.dto.UsuarioDTO;
import com.example.familia_api.modelos.mapas.IMapaEstudiante;
import com.example.familia_api.modelos.mapas.IMapaFamiliar;
import com.example.familia_api.modelos.mapas.IMapaUsuario;
import com.example.familia_api.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// AQUI LLAMAMOS A NUESTRO REPOSITORIO PARA QUE HAGA EL TRABAJO DE IR A LA COCINA, EL CHEF HACE LA COMIDA
// LA RETORNA A LOS CONTROLLADORES

@Service
public class UsuarioServicio {

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    private IMapaUsuario mapaUsuario;

    @Autowired
    private IMapaEstudiante mapaEstudiante;

    @Autowired
    private IMapaFamiliar mapaFamiliar;

    // Estamos usando nuestra dependencia para encriptar contraseñas
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioServicio() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    private UsuarioDTO mapUsuarioToDtoWithRole(Usuario usuario) {
        if (usuario instanceof Estudiante) {
            return mapaEstudiante.toDto((Estudiante) usuario);
        } else if (usuario instanceof Familiar) {
            return mapaFamiliar.toDto((Familiar) usuario);
        } else {
            // Para un Usuario base que no es Estudiante/Familiar, usar el mapper genérico
            return mapaUsuario.toDto(usuario);
        }
    }

    public UsuarioDTO guardarUsuario(Usuario datosUsuario) throws Exception {
        try {
            // El rol ya debe venir establecido en datosUsuario
            datosUsuario.setContra(passwordEncoder.encode(datosUsuario.getContra()));
            Usuario savedUser = usuarioRepositorio.save(datosUsuario);
            return mapUsuarioToDtoWithRole(savedUser);
        } catch (Exception error) {
            throw new Exception("Error al guardar el usuario: " + error.getMessage());
        }
    }

    public UsuarioDTO buscarUsuarioPorId(Integer id) throws Exception {
        try {
            Optional<Usuario> usuarioEncontrado = usuarioRepositorio.findById(id);
            if (usuarioEncontrado.isPresent()) {
                return mapUsuarioToDtoWithRole(usuarioEncontrado.get());
            } else {
                throw new Exception("Usuario no encontrado");
            }
        } catch (Exception error) {
            throw new Exception("Error al buscar el usuario: " + error.getMessage());
        }
    }

    public List<UsuarioDTO> buscarTodosLosUsuarios() throws Exception {
        try {
            return usuarioRepositorio.findAll().stream()
                    .map(this::mapUsuarioToDtoWithRole) // Usar el método auxiliar para mapear con rol
                    .collect(Collectors.toList());
        } catch (Exception error) {
            throw new Exception("Error al buscar todos los usuarios: " + error.getMessage());
        }
    }

    public UsuarioDTO loginUsuario(String correo, String contra) throws Exception {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepositorio.findByCorreo(correo);
            if (usuarioOptional.isEmpty()) {
                throw new Exception("Credenciales inválidas: Usuario no encontrado.");
            }

            Usuario usuario = usuarioOptional.get();
            if (!passwordEncoder.matches(contra, usuario.getContra())) {
                throw new Exception("Credenciales inválidas: Contraseña incorrecta.");
            }

            // Si las credenciales son correctas, mapear y devolver el DTO con el rol
            return mapUsuarioToDtoWithRole(usuario);

        } catch (Exception error) {
            throw new Exception("Fallo en la autenticación: " + error.getMessage());
        }
    }

    public UsuarioDTO buscarUsuarioPorCorreo(String correo) throws Exception {
        try {
            Optional<Usuario> usuarioEncontrado = usuarioRepositorio.findByCorreo(correo);
            if (usuarioEncontrado.isPresent()) {
                return mapUsuarioToDtoWithRole(usuarioEncontrado.get());
            } else {
                throw new Exception("Usuario no encontrado con el correo: " + correo);
            }
        } catch (Exception error) {
            throw new Exception("Error al buscar el usuario por correo: " + error.getMessage());
        }
    }

    public UsuarioDTO actualizarUsuario(Integer id, Usuario datosParaActualizar) throws Exception {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(id);
            if (usuarioOptional.isEmpty()) {
                throw new Exception("Usuario no encontrado para actualizar.");
            }
            Usuario usuarioExistente = usuarioOptional.get();

            // Actualizar los campos que se permitan. No se actualiza la contraseña aquí.
            usuarioExistente.setNombre(datosParaActualizar.getNombre());
            usuarioExistente.setCorreo(datosParaActualizar.getCorreo());

            Usuario usuarioActualizado = usuarioRepositorio.save(usuarioExistente);
            return mapUsuarioToDtoWithRole(usuarioActualizado);
        } catch (Exception error) {
            throw new Exception("Error al actualizar el usuario: " + error.getMessage());
        }
    }

    public void eliminarUsuarioPorCorreo(String correo) throws Exception {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepositorio.findByCorreo(correo);
            if (usuarioOptional.isEmpty()) {
                throw new Exception("Usuario no encontrado para eliminar.");
            }
            usuarioRepositorio.delete(usuarioOptional.get());
        } catch (Exception error) {
            throw new Exception("Error al eliminar el usuario: " + error.getMessage());
        }
    }

    public void eliminarUsuarioPorId(Integer id) throws Exception {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(id);
            if (usuarioOptional.isEmpty()) {
                throw new Exception("Usuario no encontrado para eliminar.");
            }
            usuarioRepositorio.deleteById(id);
        } catch (Exception error) {
            throw new Exception("Error al eliminar el usuario: " + error.getMessage());
        }
    }
}
