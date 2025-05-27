package com.example.sercoplus.sercoplus_soa.service;

import com.example.sercoplus.sercoplus_soa.dto.DetalleCompraDTO;
import com.example.sercoplus.sercoplus_soa.model.Compra;
import com.example.sercoplus.sercoplus_soa.model.Producto;
import com.example.sercoplus.sercoplus_soa.model.DetalleCompra;
import com.example.sercoplus.sercoplus_soa.repository.CompraRepository;
import com.example.sercoplus.sercoplus_soa.repository.DetalleCompraRepository;
import com.example.sercoplus.sercoplus_soa.repository.ProductoRepository;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class DetalleCompraService {

    private final DetalleCompraRepository detalleCompraRepository;
    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;

    public DetalleCompraService(
        DetalleCompraRepository detalleCompraRepository,
        CompraRepository compraRepository,
        ProductoRepository productoRepository
    ) {
        this.detalleCompraRepository = detalleCompraRepository;
        this.compraRepository = compraRepository;
        this.productoRepository = productoRepository;
    }

  public DetalleCompra registrarDetalle(DetalleCompraDTO dto) {
    Compra compra = compraRepository.findById(dto.getCompraId())
        .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

    Producto producto = productoRepository.findById(dto.getProductoId())
        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    if (producto.getStock() < dto.getCantidad()) {
        throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombreProducto());
    }

    // Descontar stock
    producto.setStock(producto.getStock() - dto.getCantidad());
    productoRepository.save(producto);

    // Calcular precio y subtotal desde la base de datos
    BigDecimal precioUnitario = producto.getPrecio();
    BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(dto.getCantidad()));

        // Crear el detalle
    DetalleCompra detalle = new DetalleCompra();
    detalle.setCompra(compra);
    detalle.setProducto(producto);
    detalle.setCantidad(dto.getCantidad());
    detalle.setPrecioUnitario(precioUnitario);
    detalle.setSubtotal(subtotal);

    // 1. Guardar el detalle primero
    DetalleCompra guardado = detalleCompraRepository.save(detalle);

    // 2. Luego actualizar el total
    BigDecimal totalActualizado = detalleCompraRepository.findByCompra(compra)
        .stream()
        .map(DetalleCompra::getSubtotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    compra.setTotal(totalActualizado);
    compraRepository.save(compra);

    return guardado;

}
}
