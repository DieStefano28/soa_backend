package com.example.sercoplus.sercoplus_soa.controller;

import com.example.sercoplus.sercoplus_soa.dto.LoginRequest;
import com.example.sercoplus.sercoplus_soa.dto.LoginResponse;
import com.example.sercoplus.sercoplus_soa.model.Cliente;
import com.example.sercoplus.sercoplus_soa.model.Usuario;
import com.example.sercoplus.sercoplus_soa.security.JwtUtil;
import com.example.sercoplus.sercoplus_soa.security.UsuarioDetails;
import com.example.sercoplus.sercoplus_soa.security.ClienteDetails;
import com.example.sercoplus.sercoplus_soa.security.ClienteDetailsService;
import com.example.sercoplus.sercoplus_soa.security.UsuarioDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager clienteAuthManager;
    private final AuthenticationManager usuarioAuthManager;
    private final ClienteDetailsService clienteDetailsService;
    private final UsuarioDetailsService usuarioDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(
        @Qualifier("clienteAuthManager") AuthenticationManager clienteAuthManager,
        @Qualifier("usuarioAuthManager") AuthenticationManager usuarioAuthManager,
        ClienteDetailsService clienteDetailsService,
        UsuarioDetailsService usuarioDetailsService,
        JwtUtil jwtUtil
    ) {
        this.clienteAuthManager = clienteAuthManager;
        this.usuarioAuthManager = usuarioAuthManager;
        this.clienteDetailsService = clienteDetailsService;
        this.usuarioDetailsService = usuarioDetailsService;
        this.jwtUtil = jwtUtil;
    }

  @PostMapping("/login")
public ResponseEntity<?> loginUnificado(@RequestBody LoginRequest request) {
    UserDetails userDetails;
    String tipo;
    Long idUsuario;
    Object usuarioInfo;

    try {
        clienteAuthManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getCorreoElectronico(), request.getContrasena())
        );

        Cliente cliente = clienteDetailsService.getClienteByCorreo(request.getCorreoElectronico());
        userDetails = new ClienteDetails(cliente);
        tipo = "CLIENTE";
        idUsuario = cliente.getIdCliente();
        usuarioInfo = cliente; // ✅ aquí se guarda todo el cliente

    } catch (AuthenticationException e) {
        try {
            usuarioAuthManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreoElectronico(), request.getContrasena())
            );

            Usuario usuario = usuarioDetailsService.getUsuarioByCorreo(request.getCorreoElectronico());
            userDetails = new UsuarioDetails(usuario);
            tipo = "USUARIO";
            idUsuario = usuario.getIdUsuario();
            usuarioInfo = usuario; // ✅ aquí se guarda todo el usuario

        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body("Correo o contraseña incorrectos");
        }
    }

    String token = jwtUtil.generarToken(userDetails, tipo);

    // ✅ Aquí retornas también el objeto del usuario
    return ResponseEntity.ok(new LoginResponse(token, tipo, request.getCorreoElectronico(), idUsuario, usuarioInfo));
}

}
