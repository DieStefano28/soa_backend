package com.example.sercoplus.sercoplus_soa.controller;

import com.example.sercoplus.sercoplus_soa.dto.ProveedorDTO;
import com.example.sercoplus.sercoplus_soa.model.Proveedor;
import com.example.sercoplus.sercoplus_soa.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    // ✅ Crear proveedor
    @PostMapping
    public ResponseEntity<Proveedor> crear(@RequestBody ProveedorDTO dto) {
        Proveedor proveedor = proveedorService.crearProveedor(dto);
        return ResponseEntity.status(201).body(proveedor);
    }

    // ✅ Listar todos
    @GetMapping
    public ResponseEntity<List<Proveedor>> listar() {
        return ResponseEntity.ok(proveedorService.listarProveedores());
    }

    // ✅ Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.obtenerPorId(id));
    }

    // ✅ Actualizar proveedor
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable Long id, @RequestBody ProveedorDTO dto) {
        Proveedor actualizado = proveedorService.actualizarProveedor(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    // ✅ Eliminar proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
