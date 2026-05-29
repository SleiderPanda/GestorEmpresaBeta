package com.example.GestorEmpresaBeta.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data @RequiredArgsConstructor
@Entity @Table(name = "ventas")
public class Venta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;
    private LocalDateTime fecha;
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalles;
    private Double total;
    private String estado;
}
