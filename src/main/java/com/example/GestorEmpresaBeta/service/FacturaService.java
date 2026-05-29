package com.example.GestorEmpresaBeta.service;

import com.example.GestorEmpresaBeta.model.Factura;
import com.example.GestorEmpresaBeta.model.Venta;
import com.example.GestorEmpresaBeta.repository.FacturaRepository;
import com.example.GestorEmpresaBeta.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class FacturaService {
    private final FacturaRepository facturaRepository;
    private final VentaRepository ventaRepository;
    //API personaliza para generar una factura
    public Factura generarFactura(Long idVenta) {
        try {
            if (idVenta == null || idVenta < 1) throw new IllegalArgumentException("El id de la venta no puede ser nulo o menor a 1");

            Venta venta = ventaRepository.findById(idVenta)
                    .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));

            if (facturaRepository.existsByVenta_IdVenta(idVenta))
                throw new IllegalArgumentException("Esta venta ya tiene una factura generada");

            Factura factura = new Factura();
            factura.setVenta(venta);
            factura.setFechaEmision(LocalDateTime.now());
            factura.setNumeroFactura("FAC-" + System.currentTimeMillis());
            factura.setSubtotal(venta.getTotal());
            factura.setImpuesto(venta.getTotal() * 0.19);
            factura.setTotal(factura.getSubtotal() + factura.getImpuesto());
            factura.setEstado("EMITIDA");
            return facturaRepository.save(factura);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al generar la factura: " + e.getMessage(), e);
        }
    }
    public Factura obtenerFacturaId(Long id) {
        try {
            if (id == null || id < 1) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            return facturaRepository.findById(id).orElse(null);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la factura: " + e.getMessage(), e);
        }
    }

    public Page<Factura> obtenerFacturas(int pagina, int tamanio) {
        try {
            Pageable pageable = PageRequest.of(pagina, tamanio);
            return facturaRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las facturas: " + e.getMessage(), e);
        }
    }
}
