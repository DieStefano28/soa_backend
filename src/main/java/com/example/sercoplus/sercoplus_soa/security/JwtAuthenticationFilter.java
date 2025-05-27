package com.example.sercoplus.sercoplus_soa.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ClienteDetailsService clienteDetailsService;
    private final UsuarioDetailsService usuarioDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil,
                                   ClienteDetailsService clienteDetailsService,
                                   UsuarioDetailsService usuarioDetailsService) {
        this.jwtUtil = jwtUtil;
        this.clienteDetailsService = clienteDetailsService;
        this.usuarioDetailsService = usuarioDetailsService;
    }

    @Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain)
        throws ServletException, IOException {

    String path = request.getRequestURI();  // ✅ CAMBIO CLAVE AQUÍ

    if (path.startsWith("/api/auth/") ||
        path.startsWith("/api/productos") ||
        path.startsWith("/api/categorias") ||
        path.startsWith("/api/proveedores") ||
        path.startsWith("/api/compras") ||
        path.startsWith("/api/detalle-compra") ||
        path.startsWith("/api/direccion-envio")) {

        filterChain.doFilter(request, response);
        return;
    }

    final String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
    }

    final String token = authHeader.substring(7);
    final String correo = jwtUtil.obtenerCorreoDesdeToken(token);
    final String tipo = jwtUtil.obtenerTipoDesdeToken(token);

    if (correo != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        try {
            UserDetails userDetails;

            if ("cliente".equalsIgnoreCase(tipo)) {
                userDetails = clienteDetailsService.loadUserByUsername(correo);
            } else {
                userDetails = usuarioDetailsService.loadUserByUsername(correo);
            }

            if (jwtUtil.esTokenValido(token)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (UsernameNotFoundException e) {
            // No se autentica
        }
    }

    filterChain.doFilter(request, response);
}
}
