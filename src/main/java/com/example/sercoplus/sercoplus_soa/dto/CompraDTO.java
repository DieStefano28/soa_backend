package com.example.sercoplus.sercoplus_soa.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CompraDTO {

    private Long clienteId;
    private String tipoDocumento;
    private String numeroDocumento;
    private String razonSocial;
    private BigDecimal total;

}
