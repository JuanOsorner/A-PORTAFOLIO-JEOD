package com.example.familia_api.controladores;

import com.example.familia_api.modelos.Usuario;
import com.example.familia_api.modelos.dto.LoginRequestDTO;
import com.example.familia_api.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// AQUI USAMOS NUESTROS SERVICIOS

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping
    public ResponseEntity<?> guardarUsuario(@RequestBody Usuario datos) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(usuarioServicio.guardarUsuario(datos));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(usuarioServicio.buscarUsuarioPorId(id));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarTodosLosUsuarios() {
        // Devolvemos en el body todos los usuarios que encontramos
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(usuarioServicio.buscarTodosLosUsuarios());
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // AQUI ESTAMOS USANDO REQUESTBODY PARA EXTRAER EL VALOR DEL CUERPO DE LA PETICIÓN
    // Estamos tomando un DTO para no exponer la estructura de la base de datos
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody LoginRequestDTO loginRequest) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(usuarioServicio.loginUsuario(loginRequest.getCorreo(), loginRequest.getContra()));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED) // 401 para credenciales inválidas
                    .body(error.getMessage());
        }
    }
    // PATH SIRVE PARA EXTRAER EL VALOR DE LA URL DIRECTAMENTE
    @GetMapping("/correo/{correo}")
    public ResponseEntity<?> buscarUsuarioPorCorreo(@PathVariable String correo) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(usuarioServicio.buscarUsuarioPorCorreo(correo));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario datosParaActualizar) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(usuarioServicio.actualizarUsuario(id, datosParaActualizar));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Por el momento no lo vamos a usar
    @DeleteMapping("/correo/{correo}")
    public ResponseEntity<?> eliminarUsuarioPorCorreo(@PathVariable String correo) {
        try {
            usuarioServicio.eliminarUsuarioPorCorreo(correo);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT) // 204 No Content es estándar para delete exitoso
                    .build();
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error.getMessage());
        }
    }

    // Este es el endpoint que se consume victor
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuarioPorId(@PathVariable Integer id){ // Corregido @pathVariable a @PathVariable
        try{
            usuarioServicio.eliminarUsuarioPorId(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build(); // Usar .build() para 204 No Content
        }catch (Exception error){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar el usuario: \n\n"+error.getMessage());
        }
    }
}
