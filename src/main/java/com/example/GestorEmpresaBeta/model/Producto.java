package com.example.GestorEmpresaBeta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "Inventario")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor

public class Producto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String nombreProducto;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "categoria", nullable = false)
    private CategoriaProducto categoriaProducto;

    private String descripcionProducto;
    private Integer stockProducto;
    private Double precioProducto;
}
