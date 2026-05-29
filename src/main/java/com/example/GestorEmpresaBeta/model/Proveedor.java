package com.example.GestorEmpresaBeta.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
@Entity @Table(name = "proveedores")
public class Proveedor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String documento;
    @Column(nullable = false)
    private String nombre;
    @Column(unique = true, nullable = false)
    private String correoElectronico;
    @Column(unique = true, length = 10)
    private String telefono;
}