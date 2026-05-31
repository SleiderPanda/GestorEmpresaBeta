package com.example.GestorEmpresaBeta.controller;

import com.example.GestorEmpresaBeta.model.Gasto;
import com.example.GestorEmpresaBeta.service.GastoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/gastos")
public class GastoController {
    private final GastoService gastoService;

    @PostMapping
    public ResponseEntity<?> guardarGasto(@RequestBody Gasto gasto) {
        try {
            return new ResponseEntity<>(gastoService.guardarGasto(gasto), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerGastoId(@PathVariable Long id) {
        try {
            Gasto gasto = gastoService.obtenerGastoId(id);
            if (gasto != null) {
                return new ResponseEntity<>(gasto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Gasto no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerGastos(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "10") int tamanio) {
        try {
            return new ResponseEntity<>(gastoService.obtenerGastos(pagina, tamanio), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarGasto(@PathVariable Long id) {
        try {
            boolean eliminado = gastoService.eliminarGasto(id);
            if (eliminado) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Gasto no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarGasto(@PathVariable Long id, @RequestBody Gasto datosCambiar) {
        try {
            Gasto gastoActualizado = gastoService.actualizarGasto(id, datosCambiar);
            if (gastoActualizado != null) {
                return new ResponseEntity<>(gastoActualizado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Gasto no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
