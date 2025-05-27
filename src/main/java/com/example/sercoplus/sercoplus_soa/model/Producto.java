package com.example.sercoplus.sercoplus_soa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "codigo_producto", nullable = false, unique = true)
    private String codigoProducto;

    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;

    @Column(name = "desc_producto")
    private String descProducto;

    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "ruta_img")
    private String rutaImg;

    @ManyToOne
    @JsonIgnoreProperties({"productos"}) // evita recursión infinita
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JsonIgnoreProperties({"productos"})
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    @ManyToOne
    @JsonIgnoreProperties({"productos"})
    @JoinColumn(name = "actualizado_por")
    private Usuario actualizadoPor;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}
