package com.example.sercoplus.sercoplus_soa.service;

import com.example.sercoplus.sercoplus_soa.dto.CategoriaDTO;
import com.example.sercoplus.sercoplus_soa.model.Categoria;
import com.example.sercoplus.sercoplus_soa.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // Crear categoría
    public Categoria crearCategoria(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(dto.getNombreCategoria());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setEstado(true);
        return categoriaRepository.save(categoria);
    }

    // Listar todas
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // Obtener por ID
    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    // Actualizar
    public Categoria actualizarCategoria(Long id, CategoriaDTO dto) {
        Categoria categoria = obtenerPorId(id);
        categoria.setNombreCategoria(dto.getNombreCategoria());
        categoria.setDescripcion(dto.getDescripcion());
        return categoriaRepository.save(categoria);
    }

    // Eliminar
    public void eliminarCategoria(Long id) {
        Categoria categoria = obtenerPorId(id);
        categoriaRepository.delete(categoria);
    }
}
