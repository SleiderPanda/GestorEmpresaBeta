package com.example.GestorEmpresaBeta.controller;

import com.example.GestorEmpresaBeta.service.FinanzasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RequiredArgsConstructor
@RestController @RequestMapping("/api/finanzas")
public class FinanzasController {
    private final FinanzasService finanzasService;

    @GetMapping("/balance/{mes}/{anio}")
    public ResponseEntity<Map<String, Double>> balance(@PathVariable int mes, @PathVariable int anio) {
        try {
            return new ResponseEntity<>(finanzasService.balance(mes, anio), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ganancias/{mes}/{anio}")
    public ResponseEntity<Map<String, Double>> gananciaMes(@PathVariable int mes, @PathVariable int anio) {
        try {
            return new ResponseEntity<>(finanzasService.gananciaMes(mes, anio), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/gastos/{mes}/{anio}")
    public ResponseEntity<Map<String, Double>> gastosMes(@PathVariable int mes, @PathVariable int anio) {
        try {
            return new ResponseEntity<>(finanzasService.gastosMes(mes, anio), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/gananciaDia/{fecha}")
    public ResponseEntity<Map<String, Double>> gananciaDia(@PathVariable String fecha) {
        try {
            return new ResponseEntity<>(finanzasService.gananciaDia(LocalDate.parse(fecha)), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}