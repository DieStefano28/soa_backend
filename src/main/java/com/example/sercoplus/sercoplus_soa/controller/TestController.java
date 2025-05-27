package com.example.sercoplus.sercoplus_soa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/protegido")
    public String accesoProtegido() {
        return "✅ Acceso autorizado con token válido.";
    }
}
