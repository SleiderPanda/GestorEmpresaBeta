package com.example.GestorEmpresaBeta.repository;

import com.example.GestorEmpresaBeta.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    //METODO CUSTOM para verificar que una venta no tenga ya una factura or el OnebyOne
    boolean existsByVenta_IdVenta(Long idVenta);
}
