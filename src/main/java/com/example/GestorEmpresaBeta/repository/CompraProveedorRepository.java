package com.example.GestorEmpresaBeta.repository;

import com.example.GestorEmpresaBeta.model.CompraProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraProveedorRepository extends JpaRepository<CompraProveedor, Long>{
    @Query("SELECT c FROM CompraProveedor c WHERE MONTH(c.fecha) = :mes AND YEAR(c.fecha) = :anio")
    List<CompraProveedor> findByMesYAnio(@Param("mes") int mes, @Param("anio") int anio);
}
