// Este archivo maneja toda la logica data-relacional del modelo de usuario

package data.repositories;

// Importamos nuestra configuracion a la base de datos
import config.DatabaseConfig;
// Importamos nuestra entidad de usuario
import data.models.ModeloUsuario;
// Importamos sql para manejar los querys
import java.sql.*;

public class RepoUsuario {
    /**
     * Función recibe los datos de un usuario y los guarda en la base de datos
     *
     * parametros: ModeloUsuario
     **/
    public void guardar_usuario(ModeloUsuario user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
        }
    }
}