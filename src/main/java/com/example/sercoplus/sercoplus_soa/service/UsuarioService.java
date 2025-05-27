package com.example.sercoplus.sercoplus_soa.service;

import com.example.sercoplus.sercoplus_soa.model.Usuario;
import com.example.sercoplus.sercoplus_soa.model.Rol;
import com.example.sercoplus.sercoplus_soa.repository.UsuarioRepository;
import com.example.sercoplus.sercoplus_soa.repository.RolRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> getUsuarioByCorreoElectronico(String correoElectronico) {
        return usuarioRepository.findByCorreoElectronico(correoElectronico);
    }

    public Usuario saveUsuario(Usuario usuario) {
        if (usuario.getRol() == null || usuario.getRol().getId() == null) {
            throw new RuntimeException("El rol es obligatorio");
        }

        Rol rol = rolRepository.findById(usuario.getRol().getId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));

        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setApellidos(usuarioDetails.getApellidos());
        usuario.setCorreoElectronico(usuarioDetails.getCorreoElectronico());

        if (usuarioDetails.getContrasena() != null && !usuarioDetails.getContrasena().isEmpty()) {
            usuario.setContrasena(passwordEncoder.encode(usuarioDetails.getContrasena()));
        }

        if (usuarioDetails.getRol() == null || usuarioDetails.getRol().getId() == null) {
            throw new RuntimeException("El rol es obligatorio para actualizar");
        }

        Rol rol = rolRepository.findById(usuarioDetails.getRol().getId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        usuario.setRol(rol);
        usuario.setEstado(usuarioDetails.getEstado());
        usuario.setFechaCreacion(usuarioDetails.getFechaCreacion());

        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
        usuarioRepository.delete(usuario);
    }
}
