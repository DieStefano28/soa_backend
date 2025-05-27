package com.example.sercoplus.sercoplus_soa.security;

import com.example.sercoplus.sercoplus_soa.model.Cliente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class ClienteDetails implements UserDetails {

    private final Cliente cliente;

    public ClienteDetails(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return cliente.getContrasena();
    }

    @Override
    public String getUsername() {
        return cliente.getCorreoElectronico();
    }

    @Override
    public boolean isAccountNonExpired() {
        return cliente.getEstado();
    }

    @Override
    public boolean isAccountNonLocked() {
        return cliente.getEstado();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return cliente.getEstado();
    }

    @Override
    public boolean isEnabled() {
        return cliente.getEstado();
    }
}
