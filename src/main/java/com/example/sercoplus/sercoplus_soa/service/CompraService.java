package com.example.sercoplus.sercoplus_soa.service;

import com.example.sercoplus.sercoplus_soa.dto.CompraDTO;
import com.example.sercoplus.sercoplus_soa.model.Cliente;
import com.example.sercoplus.sercoplus_soa.model.Compra;
import com.example.sercoplus.sercoplus_soa.repository.ClienteRepository;
import com.example.sercoplus.sercoplus_soa.repository.CompraRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ClienteRepository clienteRepository;

    public CompraService(CompraRepository compraRepository, ClienteRepository clienteRepository) {
        this.compraRepository = compraRepository;
        this.clienteRepository = clienteRepository;
    }

    public Compra registrarCompra(CompraDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setTipoDocumento(dto.getTipoDocumento());
        compra.setNumeroDocumento(dto.getNumeroDocumento());
        compra.setRazonSocial(dto.getRazonSocial());
        compra.setTotal(dto.getTotal());
        compra.setFechaCompra(LocalDateTime.now());

        return compraRepository.save(compra);
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
