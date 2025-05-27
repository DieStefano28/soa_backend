package com.example.sercoplus.sercoplus_soa.controller;

import com.example.sercoplus.sercoplus_soa.model.Rol;
import com.example.sercoplus.sercoplus_soa.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    @GetMapping
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }
}
