package com.example.GestorEmpresaBeta.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Data @RequiredArgsConstructor
@Entity @Table(name = "facturas")
public class Factura {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String numeroFactura;
    private LocalDateTime fechaEmision;
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "venta", nullable = false)
    private Venta venta;
    private Double subtotal;
    private Double impuesto;
    private Double total;
    private String estado;
}