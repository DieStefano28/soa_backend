package com.example.sercoplus.sercoplus_soa.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DetalleCompraDTO {

    private Long compraId;         // Puede ser opcional si solo se usa desde CompraDTO
    private Long productoId;
    private int cantidad;
    private BigDecimal  precioUnitario;
    private BigDecimal subtotal;
}
