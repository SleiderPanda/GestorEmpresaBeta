package com.example.GestorEmpresaBeta.controller;

import com.example.GestorEmpresaBeta.model.Factura;
import com.example.GestorEmpresaBeta.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController @RequestMapping("/api/facturas")
public class FacturaController {
    private final FacturaService facturaService;

    @PostMapping("/generar/{idVenta}")
    public ResponseEntity<Factura> generarFactura(@PathVariable Long idVenta) {
        try {
            return new ResponseEntity<>(facturaService.generarFactura(idVenta), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerFacturaId(@PathVariable Long id) {
        try {
            Factura factura = facturaService.obtenerFacturaId(id);
            if (factura != null) {
                return new ResponseEntity<>(factura, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Factura>> obtenerFacturas(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "10") int tamanio) {
        try {
            return new ResponseEntity<>(facturaService.obtenerFacturas(pagina, tamanio), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}