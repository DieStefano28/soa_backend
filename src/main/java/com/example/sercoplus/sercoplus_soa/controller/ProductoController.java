package com.example.sercoplus.sercoplus_soa.controller;

import com.example.sercoplus.sercoplus_soa.dto.ProductoDTO;
import com.example.sercoplus.sercoplus_soa.dto.StockUpdateDTO;
import com.example.sercoplus.sercoplus_soa.model.Producto;
import com.example.sercoplus.sercoplus_soa.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // POST: Crear producto
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody ProductoDTO dto) {
        Producto nuevo = productoService.crearProducto(dto);
        return ResponseEntity.status(201).body(nuevo);
    }

    // GET: Listar todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    // GET: Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    // PUT: Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        Producto actualizado = productoService.actualizarProducto(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    // PUT: Aumentar stock
    @PutMapping("/{id}/stock")
    public ResponseEntity<Producto> aumentarStock(
            @PathVariable Long id,
            @RequestBody StockUpdateDTO dto) {
        Producto actualizado = productoService.aumentarStock(id, dto.getCantidad());
        return ResponseEntity.ok(actualizado);
    }

    // DELETE: Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
} 
