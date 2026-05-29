package com.example.GestorEmpresaBeta.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data @RequiredArgsConstructor
@Entity @Table(name = "comprasProveedor")
public class CompraProveedor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "proveedor", nullable = false)
    private Proveedor proveedor;
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<DetalleCompra> detalles;
    private Double total;
    private LocalDateTime fecha;
    private String estado;
}