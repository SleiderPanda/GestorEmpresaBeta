package com.example.GestorEmpresaBeta.repository;

import com.example.GestorEmpresaBeta.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
    @Query("SELECT g FROM Gasto g WHERE MONTH(g.fecha) = :mes AND YEAR(g.fecha) = :anio")
    List<Gasto> findByMesYAnio(@Param("mes") int mes, @Param("anio") int anio);
}