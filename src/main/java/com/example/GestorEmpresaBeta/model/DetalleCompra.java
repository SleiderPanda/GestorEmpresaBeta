package com.example.GestorEmpresaBeta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
@Entity
@Table(name = "detalle_compras")
public class DetalleCompra {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "compra", nullable = false)
    private CompraProveedor compra;
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "producto", nullable = false)
    private Producto producto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}
