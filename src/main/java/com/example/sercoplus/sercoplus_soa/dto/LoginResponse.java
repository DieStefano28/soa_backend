package com.example.sercoplus.sercoplus_soa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String tipo;
    private String correoElectronico;
    private Long idUsuario;
    private Object usuario; // ✅ ¡aquí el cambio!
}
