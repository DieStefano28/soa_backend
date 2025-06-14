package com.example.sercoplus.sercoplus_soa.repository;

import com.example.sercoplus.sercoplus_soa.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombreRol(String nombreRol);
}
