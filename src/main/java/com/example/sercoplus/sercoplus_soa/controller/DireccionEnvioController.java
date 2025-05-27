package com.example.sercoplus.sercoplus_soa.controller;

import com.example.sercoplus.sercoplus_soa.dto.DireccionEnvioDTO;
import com.example.sercoplus.sercoplus_soa.model.DireccionEnvio;
import com.example.sercoplus.sercoplus_soa.service.DireccionEnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direccion-envio")
@CrossOrigin(origins = "http://localhost:3000") // ajusta si usas otro puerto o dominio
public class DireccionEnvioController {

    @Autowired
    private DireccionEnvioService direccionEnvioService;

    // Crear nueva dirección de envío
    @PostMapping
    public ResponseEntity<DireccionEnvio> registrar(@RequestBody DireccionEnvioDTO dto) {
        DireccionEnvio creada = direccionEnvioService.registrarDireccion(dto);
        return ResponseEntity.status(201).body(creada);
    }

    // (Opcional) listar todas
    @GetMapping
    public ResponseEntity<List<DireccionEnvio>> listar() {
        return ResponseEntity.ok(direccionEnvioService.listarDirecciones());
    }
}
