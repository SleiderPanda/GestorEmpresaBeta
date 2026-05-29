package com.example.GestorEmpresaBeta.repository;

import com.example.GestorEmpresaBeta.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}
