package com.example.sercoplus.sercoplus_soa.security;

import com.example.sercoplus.sercoplus_soa.model.Cliente;
import com.example.sercoplus.sercoplus_soa.repository.ClienteRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class ClienteDetailsService implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    public ClienteDetailsService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correoElectronico) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByCorreoElectronico(correoElectronico)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado: " + correoElectronico));
        return new ClienteDetails(cliente);
    }
    public Cliente getClienteByCorreo(String correo) {
    return clienteRepository.findByCorreoElectronico(correo)
            .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado: " + correo));
}

}
