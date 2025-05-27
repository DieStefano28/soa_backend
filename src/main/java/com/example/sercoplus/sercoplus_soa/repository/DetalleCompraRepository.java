package com.example.sercoplus.sercoplus_soa.repository;

import com.example.sercoplus.sercoplus_soa.model.Compra;
import com.example.sercoplus.sercoplus_soa.model.DetalleCompra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Long> {
    List<DetalleCompra> findByCompra(Compra compra);

}

