package com.example.sercoplus.sercoplus_soa.controller;

import com.example.sercoplus.sercoplus_soa.dto.CategoriaDTO;
import com.example.sercoplus.sercoplus_soa.model.Categoria;
import com.example.sercoplus.sercoplus_soa.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:3000") // Cambia si usas otro puerto
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Crear categoría
    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody CategoriaDTO dto) {
        Categoria nueva = categoriaService.crearCategoria(dto);
        return ResponseEntity.status(201).body(nueva);
    }

    // Listar todas
    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.obtenerPorId(id));
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
        Categoria actualizada = categoriaService.actualizarCategoria(id, dto);
        return ResponseEntity.ok(actualizada);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}

