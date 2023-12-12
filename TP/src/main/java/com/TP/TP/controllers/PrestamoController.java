package com.TP.TP.controllers;
import com.TP.TP.models.dtos.PrestamoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.TP.TP.models.Prestamo;
import com.TP.TP.services.PrestamoService;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private final PrestamoService service;

    public PrestamoController(PrestamoService service) {
        this.service = service;
    }

    @PostMapping
        public ResponseEntity<PrestamoDTO> createPrestamo(@RequestBody PrestamoDTO prestamoDtoRequest) {
        PrestamoDTO prestamo = service.createPrestamo(prestamoDtoRequest,30);
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamo);
    }

    @GetMapping
    public ResponseEntity<List<PrestamoDTO>> getPrestamos(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getPrestamos());
    }
}
