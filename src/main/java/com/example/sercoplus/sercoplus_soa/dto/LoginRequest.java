// src/main/java/com/example/sercoplus/sercoplus_soa/dto/LoginRequest.java
package com.example.sercoplus.sercoplus_soa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String correoElectronico;
    private String contrasena;
}
