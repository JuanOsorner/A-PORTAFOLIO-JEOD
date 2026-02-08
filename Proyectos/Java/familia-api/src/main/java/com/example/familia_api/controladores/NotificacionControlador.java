package com.example.familia_api.controladores;

import com.example.familia_api.servicios.NotificacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionControlador {

    @Autowired
    private NotificacionServicio notificacionServicio;

    @PostMapping
    public ResponseEntity<?> crearNotificacion(@RequestBody Map<String, Object> requestBody) {
        try {
            Integer familiarId = (Integer) requestBody.get("familiarId");
            String mensaje = (String) requestBody.get("mensaje");

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(notificacionServicio.crearNotificacion(familiarId, mensaje));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @GetMapping("/familiar/{familiarId}")
    public ResponseEntity<?> buscarNotificacionesPorFamiliar(@PathVariable Integer familiarId) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(notificacionServicio.buscarNotificacionesPorFamiliar(familiarId));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}
