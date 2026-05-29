package com.example.GestorEmpresaBeta.repository;

import com.example.GestorEmpresaBeta.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Long> {
}
