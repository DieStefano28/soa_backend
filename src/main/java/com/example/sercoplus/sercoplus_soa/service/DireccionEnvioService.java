package com.example.sercoplus.sercoplus_soa.service;

import com.example.sercoplus.sercoplus_soa.dto.DireccionEnvioDTO;
import com.example.sercoplus.sercoplus_soa.model.Compra;
import com.example.sercoplus.sercoplus_soa.model.DireccionEnvio;
import com.example.sercoplus.sercoplus_soa.repository.CompraRepository;
import com.example.sercoplus.sercoplus_soa.repository.DireccionEnvioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DireccionEnvioService {

    private final DireccionEnvioRepository direccionEnvioRepository;
    private final CompraRepository compraRepository;

    public DireccionEnvioService(DireccionEnvioRepository direccionEnvioRepository,
                                  CompraRepository compraRepository) {
        this.direccionEnvioRepository = direccionEnvioRepository;
        this.compraRepository = compraRepository;
    }

    // Crear dirección de envío
    public DireccionEnvio registrarDireccion(DireccionEnvioDTO dto) {
        Compra compra = compraRepository.findById(dto.getIdCompra())
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        DireccionEnvio direccion = new DireccionEnvio();
        direccion.setCompra(compra);
        direccion.setNombre(dto.getNombre());
        direccion.setApellidos(dto.getApellidos());
        direccion.setDireccion(dto.getDireccion());
        direccion.setCiudad(dto.getCiudad());
        direccion.setPais(dto.getPais());
        direccion.setTelefono(dto.getTelefono());
        direccion.setDni(dto.getDni());
        direccion.setFechaRegistro(LocalDateTime.now());

        return direccionEnvioRepository.save(direccion);
    }

    // Listar direcciones (opcional)
    public List<DireccionEnvio> listarDirecciones() {
        return direccionEnvioRepository.findAll();
    }
}
