package data.repositories;

import config.DatabaseConfig;
import data.models.ModeloUsuario;
import logica.interfaces.IUsuarioRepositorio; // Importamos tu interfaz
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepoUsuario implements IUsuarioRepositorio {

    @Override
    public void guardar(ModeloUsuario user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<ModeloUsuario> obtenerTodos() throws SQLException {
        List<ModeloUsuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(new ModeloUsuario(
                        rs.getLong("id"),
                        rs.getString("name"), // Corregido: getString
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }
        }
        return usuarios;
    }
}