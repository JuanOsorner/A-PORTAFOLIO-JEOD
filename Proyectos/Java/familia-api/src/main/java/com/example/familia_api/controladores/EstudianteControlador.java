package com.example.familia_api.controladores;

import com.example.familia_api.modelos.dto.EstudianteDetalleDTO;
import com.example.familia_api.servicios.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteControlador {

    @Autowired
    private EstudianteServicio estudianteServicio;

    @PostMapping("/detalles")
    public ResponseEntity<?> guardarEstudianteDetalle(@RequestBody EstudianteDetalleDTO detalleDTO) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(estudianteServicio.guardarEstudianteDetalle(detalleDTO));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEstudiantePorId(@PathVariable Integer id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(estudianteServicio.buscarEstudiantePorId(id));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarTodosLosEstudiantes() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(estudianteServicio.buscarTodosLosEstudiantes());
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}
