package com.example.familia_api.controladores;

import com.example.familia_api.modelos.dto.FamiliarDetalleDTO;
import com.example.familia_api.servicios.FamiliarServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/familiares")
public class FamiliarControlador {

    @Autowired
    private FamiliarServicio familiarServicio;

    @PostMapping("/detalles") // Nuevo endpoint para guardar/actualizar detalles
    public ResponseEntity<?> guardarFamiliarDetalle(@RequestBody FamiliarDetalleDTO detalleDTO) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(familiarServicio.guardarFamiliarDetalle(detalleDTO));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarFamiliarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(familiarServicio.buscarFamiliarPorId(id));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarTodosLosFamiliares() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(familiarServicio.buscarTodosLosFamiliares());
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}
