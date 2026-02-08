package com.example.familia_api.controladores;

import com.example.familia_api.modelos.dto.VinculoDTO;
import com.example.familia_api.servicios.VinculoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vinculos")
public class VinculoControlador {

    @Autowired
    private VinculoServicio vinculoServicio;

    @PostMapping
    public ResponseEntity<?> crearVinculo(@RequestBody VinculoDTO vinculoDTO) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(vinculoServicio.crearVinculo(vinculoDTO));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarVinculos() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(vinculoServicio.listarVinculos());
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}
