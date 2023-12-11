package com.TP.TP.controllers;
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
    public ResponseEntity<Prestamo> crearPrestamo(@RequestBody Prestamo prestamoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearPrestamo(prestamoRequest,3L,30));
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> traer(){
        return ResponseEntity.status(HttpStatus.OK).body(service.traer());
    }
}
