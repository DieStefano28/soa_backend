package com.example.sercoplus.sercoplus_soa.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class CompraDTO {

    private Long clienteId;
    private BigDecimal total;
    private List<DetalleCompraDTO> detalles; // 👈 importante


}
