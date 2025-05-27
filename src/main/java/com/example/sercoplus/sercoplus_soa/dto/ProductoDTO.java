package com.example.sercoplus.sercoplus_soa.dto;

import java.math.BigDecimal;
import lombok.Data;
@Data

public class ProductoDTO {

    private String codigoProducto;
    private String nombreProducto;
    private String descProducto;
    private BigDecimal precio;
    private Integer stock;
    private String rutaImg;
    private Long categoriaId;
    private Long proveedorId;
    private Long actualizadoPorId;
    private Boolean estado;

}
