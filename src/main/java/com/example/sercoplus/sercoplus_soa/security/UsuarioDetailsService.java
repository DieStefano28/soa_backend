package com.example.sercoplus.sercoplus_soa.security;

import com.example.sercoplus.sercoplus_soa.model.Usuario;
import com.example.sercoplus.sercoplus_soa.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        System.out.println("🔍 Buscando usuario con correo: " + correo);

        Usuario usuario = usuarioRepository.findByCorreoElectronico(correo)
                .orElseThrow(() -> {
                    System.out.println("❌ Usuario no encontrado");
                    return new UsernameNotFoundException("Usuario no encontrado: " + correo);
                });

        System.out.println("✅ Usuario encontrado");
        System.out.println("👉 Estado: " + usuario.getEstado());
        System.out.println("🔐 Contraseña en BD (encriptada): " + usuario.getContrasena());

        return new UsuarioDetails(usuario);
    }
    public Usuario getUsuarioByCorreo(String correo) {
    return usuarioRepository.findByCorreoElectronico(correo)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + correo));
}

}
