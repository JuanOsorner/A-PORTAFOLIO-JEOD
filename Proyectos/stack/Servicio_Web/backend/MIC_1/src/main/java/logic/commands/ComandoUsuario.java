package logica.comandos;

import data.entities.ModeloUsuario;
import logica.interfaces.IUsuarioRepositorio;
import transfer.dtos.UsuarioDTO;
import transfer.mappers.UsuarioMapper;

public class ComandoUsuario {
    // Definimos la dependencia como la interfaz, no la clase concreta
    private final IUsuarioRepositorio repoUsuario;

    // Inyección por constructor: Mayor control y facilidad de testing
    public ComandoUsuario(IUsuarioRepositorio repoUsuario) {
        this.repoUsuario = repoUsuario;
    }

    public void crearUsuario(ModeloUsuario usuario) throws Exception {
        // Aquí podrías añadir lógica de negocio adicional (ej. validar dominio de email)
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            throw new Exception("Email inválido para el registro");
        }
        repoUsuario.guardar(usuario);
    }

    public UsuarioDTO obtenerUsuarioParaVista(ModeloUsuario entidad) {
        return UsuarioMapper.toDTO(entidad);
    }
}