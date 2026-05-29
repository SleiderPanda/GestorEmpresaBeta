package com.example.GestorEmpresaBeta.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Data @RequiredArgsConstructor
@Entity @Table(name = "gastos")
public class Gasto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private Double monto;
    private LocalDateTime fecha;
}