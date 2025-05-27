package com.example.sercoplus.sercoplus_soa.security;

import com.example.sercoplus.sercoplus_soa.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class UsuarioDetails implements UserDetails {

    private final Usuario usuario;

    public UsuarioDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(usuario.getRol().getNombreRol()));
    }

    @Override
    public String getPassword() {
        return usuario.getContrasena();
    }

    @Override
    public String getUsername() {
        return usuario.getCorreoElectronico();
    }

    @Override
    public boolean isAccountNonExpired() {
        return usuario.getEstado();
    }

    @Override
    public boolean isAccountNonLocked() {
        return usuario.getEstado();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return usuario.getEstado();
    }

    @Override
    public boolean isEnabled() {
        return usuario.getEstado();
    }
}
