package com.example.sercoplus.sercoplus_soa.repository;

import com.example.sercoplus.sercoplus_soa.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCorreoElectronico(String correoElectronico);
    boolean existsByCorreoElectronico(String correoElectronico);  // <-- Agrega esta línea
}
