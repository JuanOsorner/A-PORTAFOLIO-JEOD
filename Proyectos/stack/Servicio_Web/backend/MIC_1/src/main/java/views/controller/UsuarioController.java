package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.models.ModeloUsuario; // Ver si podemos simplificar mas esto
import logica.comandos.ComandoUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/usuarios")
public class UsuarioController extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ComandoUsuario comandoUsuario = new ComandoUsuario();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1. Recibir datos del cliente [cite: 44]
            ModeloUsuario usuario = objectMapper.readValue(req.getInputStream(), ModeloUsuario.class);

            // 2. Delegar a la capa de Logica/Comandos [cite: 9, 25]
            comandoUsuario.crearUsuario(usuario);

            // 3. Devolver respuesta [cite: 45]
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"mensaje\": \"Usuario procesado por la capa de lógica\"}");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}