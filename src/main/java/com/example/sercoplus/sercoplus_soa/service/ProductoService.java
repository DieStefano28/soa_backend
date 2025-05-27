package com.example.sercoplus.sercoplus_soa.service;

import com.example.sercoplus.sercoplus_soa.dto.ProductoDTO;
import com.example.sercoplus.sercoplus_soa.model.Producto;
import com.example.sercoplus.sercoplus_soa.repository.CategoriaRepository;
import com.example.sercoplus.sercoplus_soa.repository.ProductoRepository;
import com.example.sercoplus.sercoplus_soa.repository.ProveedorRepository;
import com.example.sercoplus.sercoplus_soa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear producto
    public Producto crearProducto(ProductoDTO dto) {
        validarCampos(dto);

        Producto producto = new Producto();
        mapearDtoAEntidad(dto, producto);
        producto.setFechaCreacion(LocalDateTime.now());
        producto.setFechaActualizacion(LocalDateTime.now());

        return productoRepository.save(producto);
    }

    // Listar todos los productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Obtener producto por ID
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    // Actualizar producto
    public Producto actualizarProducto(Long id, ProductoDTO dto) {
    validarCampos(dto);

    Producto producto = obtenerPorId(id);

    // ⬇️ Sumar el stock actual + nuevo ingresado
    int nuevoStock = producto.getStock() + dto.getStock();
    producto.setStock(nuevoStock);

    // Seteo de campos restantes
    producto.setCodigoProducto(dto.getCodigoProducto());
    producto.setNombreProducto(dto.getNombreProducto());
    producto.setDescProducto(dto.getDescProducto());
    producto.setPrecio(dto.getPrecio());
    producto.setRutaImg(dto.getRutaImg());
    producto.setFechaActualizacion(LocalDateTime.now());

    producto.setCategoria(categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada")));

    producto.setProveedor(proveedorRepository.findById(dto.getProveedorId())
            .orElseThrow(() -> new RuntimeException("Proveedor no encontrado")));

    producto.setActualizadoPor(usuarioRepository.findById(dto.getActualizadoPorId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

    producto.setEstado(dto.getEstado());


    return productoRepository.save(producto);
}


    // Eliminar producto
    public void eliminarProducto(Long id) {
        Producto producto = obtenerPorId(id);
        productoRepository.delete(producto);
    }

    public Producto aumentarStock(Long idProducto, int cantidad) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setStock(producto.getStock() + cantidad);
        return productoRepository.save(producto);
    }

    // Validaciones comunes
    private void validarCampos(ProductoDTO dto) {
        if (dto.getCategoriaId() == null) {
            throw new IllegalArgumentException("El ID de categoría no puede ser nulo");
        }
        if (dto.getProveedorId() == null) {
            throw new IllegalArgumentException("El ID de proveedor no puede ser nulo");
        }
        if (dto.getActualizadoPorId() == null) {
            throw new IllegalArgumentException("El ID del usuario que actualiza no puede ser nulo");
        }
    }

    // Mapear DTO a entidad Producto
    private void mapearDtoAEntidad(ProductoDTO dto, Producto producto) {
        producto.setCodigoProducto(dto.getCodigoProducto());
        producto.setNombreProducto(dto.getNombreProducto());
        producto.setDescProducto(dto.getDescProducto());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setRutaImg(dto.getRutaImg());
        producto.setEstado(dto.getEstado());


        producto.setCategoria(categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada")));

        producto.setProveedor(proveedorRepository.findById(dto.getProveedorId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado")));

        producto.setActualizadoPor(usuarioRepository.findById(dto.getActualizadoPorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
    }
}
