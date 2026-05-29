package com.example.GestorEmpresaBeta.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
@Entity @Table(name = "DetalleVentas")
public class DetalleVenta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleventa;
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "venta", nullable = false)
    private Venta venta;
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "producto", nullable = false)
    private Producto producto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}
