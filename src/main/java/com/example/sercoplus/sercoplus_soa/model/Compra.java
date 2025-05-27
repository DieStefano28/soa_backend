package com.example.sercoplus.sercoplus_soa.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long idCompra;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "tipo_documento", nullable = false)
    private String tipoDocumento;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "fecha_compra")
    private LocalDateTime fechaCompra;

    @PrePersist
    public void prePersist() {
        fechaCompra = LocalDateTime.now();
    }
}
