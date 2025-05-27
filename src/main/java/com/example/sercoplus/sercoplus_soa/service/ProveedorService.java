package com.example.sercoplus.sercoplus_soa.service;

import com.example.sercoplus.sercoplus_soa.dto.ProveedorDTO;
import com.example.sercoplus.sercoplus_soa.model.Proveedor;
import com.example.sercoplus.sercoplus_soa.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public Proveedor crearProveedor(ProveedorDTO dto) {
        Proveedor proveedor = new Proveedor();
        proveedor.setRazonSocial(dto.getRazonSocial());
        proveedor.setRuc(dto.getRuc());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setCorreoElectronico(dto.getCorreoElectronico());
        proveedor.setEstado(true);

        return proveedorRepository.save(proveedor);
    }

    public List<Proveedor> listarProveedores() {
        return proveedorRepository.findAll();
    }

    public Proveedor obtenerPorId(Long id) {
        return proveedorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    public Proveedor actualizarProveedor(Long id, ProveedorDTO dto) {
        Proveedor proveedor = obtenerPorId(id);
        proveedor.setRazonSocial(dto.getRazonSocial());
        proveedor.setRuc(dto.getRuc());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setCorreoElectronico(dto.getCorreoElectronico());
        proveedor.setEstado(dto.getEstado());
        return proveedorRepository.save(proveedor);
    }

    public void eliminarProveedor(Long id) {
        Proveedor proveedor = obtenerPorId(id);
        proveedorRepository.delete(proveedor);
    }
}
