package com.example.sercoplus.sercoplus_soa.service;

import com.example.sercoplus.sercoplus_soa.model.Cliente;
import com.example.sercoplus.sercoplus_soa.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registro con validación y cifrado
    public Cliente registrarCliente(Cliente cliente) {
        if(clienteRepository.findByCorreoElectronico(cliente.getCorreoElectronico()).isPresent()) {
            throw new RuntimeException("Cliente ya registrado");
        }
        cliente.setContrasena(passwordEncoder.encode(cliente.getContrasena()));
        cliente.setEstado(true);
        return clienteRepository.save(cliente);
    }

    // Obtener todos los clientes
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    // Obtener cliente por ID
    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    // Actualizar cliente
    public Cliente actualizarCliente(Long id, Cliente clienteDetalles) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombre(clienteDetalles.getNombre());
        cliente.setApellidos(clienteDetalles.getApellidos());
        cliente.setCorreoElectronico(clienteDetalles.getCorreoElectronico());

        // Si la contraseña viene distinta, la ciframos
        if(clienteDetalles.getContrasena() != null && !clienteDetalles.getContrasena().isEmpty()) {
            cliente.setContrasena(passwordEncoder.encode(clienteDetalles.getContrasena()));
        }

        cliente.setEstado(clienteDetalles.getEstado());
        cliente.setFechaNacimiento(clienteDetalles.getFechaNacimiento());
        cliente.setEstado(true);
        return clienteRepository.save(cliente);
    }

    // Eliminar cliente
    public void eliminarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        clienteRepository.delete(cliente);
    }
}
