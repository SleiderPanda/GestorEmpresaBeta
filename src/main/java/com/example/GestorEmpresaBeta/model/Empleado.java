package com.example.GestorEmpresaBeta.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
@Entity @Table(name = "empleados")
public class Empleado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpleado;
    @Column(nullable = false, unique = true)
    private String documento;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(unique = true,nullable = false)
    private String correoElectronico;
    @Column(nullable = false)
    private Double salario;
    @Column(unique = true,length = 10)
    private String telefono;
    @Column(nullable = false)
    private String cargo;
}
