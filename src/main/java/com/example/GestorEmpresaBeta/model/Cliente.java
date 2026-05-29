package com.example.GestorEmpresaBeta.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
@Entity @Table(name = "clientes")
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idProducto;
    @Column(nullable = false, unique = true)
    String documento;
    @Column(nullable = false)
    String nombre;
    @Column(nullable = false)
    String apellido;
    @Column(unique = true)
    String correoElectronico;
    @Column(unique = true,length = 10)
    String telefono;
}
