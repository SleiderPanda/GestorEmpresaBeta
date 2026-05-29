package com.example.GestorEmpresaBeta.repository;

import com.example.GestorEmpresaBeta.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    // VentaRepository.java
    @Query("SELECT v FROM Venta v WHERE MONTH(v.fecha) = :mes AND YEAR(v.fecha) = :anio")
    List<Venta> findByMesYAnio(@Param("mes") int mes, @Param("anio") int anio);

    @Query("SELECT v FROM Venta v WHERE DATE(v.fecha) = :fecha")
    List<Venta> findByFecha(@Param("fecha") LocalDate fecha);
}
