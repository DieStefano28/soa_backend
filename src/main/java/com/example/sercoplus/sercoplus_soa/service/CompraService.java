package com.example.sercoplus.sercoplus_soa.service;

import com.example.sercoplus.sercoplus_soa.dto.CompraDTO;
import com.example.sercoplus.sercoplus_soa.dto.DetalleCompraDTO;
import com.example.sercoplus.sercoplus_soa.model.Cliente;
import com.example.sercoplus.sercoplus_soa.model.Compra;
import com.example.sercoplus.sercoplus_soa.model.DetalleCompra;
import com.example.sercoplus.sercoplus_soa.model.Producto;
import com.example.sercoplus.sercoplus_soa.repository.ClienteRepository;
import com.example.sercoplus.sercoplus_soa.repository.CompraRepository;
import com.example.sercoplus.sercoplus_soa.repository.DetalleCompraRepository;
import com.example.sercoplus.sercoplus_soa.repository.ProductoRepository;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final DetalleCompraRepository detalleCompraRepository;

    public CompraService(
            CompraRepository compraRepository,
            ClienteRepository clienteRepository,
            ProductoRepository productoRepository,
            DetalleCompraRepository detalleCompraRepository
    ) {
        this.compraRepository = compraRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.detalleCompraRepository = detalleCompraRepository;
    }

    public Compra registrarCompra(CompraDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setTotal(dto.getTotal());
        compra.setFechaCompra(LocalDateTime.now());
        // Primero guardamos la compra
        Compra compraGuardada = compraRepository.save(compra);

        // Luego, registramos los detalles
        for (DetalleCompraDTO detalleDTO : dto.getDetalles()) {
            Producto producto = productoRepository.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DetalleCompra detalle = new DetalleCompra();
            detalle.setCompra(compraGuardada);
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());
            detalle.setSubtotal(detalleDTO.getSubtotal());

            detalleCompraRepository.save(detalle);
        }

        return compraGuardada;
    }

    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

    public Compra obtenerCompraPorId(Long id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));
    }

    public void eliminarCompra(Long id) {
        Compra compra = obtenerCompraPorId(id);
        compraRepository.delete(compra);
    }
}
