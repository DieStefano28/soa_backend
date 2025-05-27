package com.example.sercoplus.sercoplus_soa.controller;

import com.example.sercoplus.sercoplus_soa.dto.DetalleCompraDTO;
import com.example.sercoplus.sercoplus_soa.model.DetalleCompra;
import com.example.sercoplus.sercoplus_soa.service.DetalleCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detalle-compra")
@CrossOrigin(origins = "http://localhost:3000")
public class DetalleCompraController {

    @Autowired
    private DetalleCompraService detalleCompraService;

    @PostMapping
    public ResponseEntity<DetalleCompra> registrar(@RequestBody DetalleCompraDTO dto) {
        DetalleCompra creado = detalleCompraService.registrarDetalle(dto);
        return ResponseEntity.status(201).body(creado);
    }
}
