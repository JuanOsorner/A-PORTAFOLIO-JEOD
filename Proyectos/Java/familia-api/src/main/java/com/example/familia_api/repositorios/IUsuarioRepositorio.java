package com.example.familia_api.repositorios;

import com.example.familia_api.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    // Una sola consulta derivadad
    Optional<Usuario> findByCorreo(String correo);
}
