package com.example.GestorEmpresaBeta.controller;

import com.example.GestorEmpresaBeta.model.Venta;
import com.example.GestorEmpresaBeta.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController @RequestMapping("/api/ventas")
public class VentaController {
    private final VentaService ventaService;
    @PostMapping
    public ResponseEntity<?> guardarVenta(@RequestBody Venta venta){
        try {
            return new ResponseEntity<>(ventaService.guardarVenta(venta), HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVentaId(@PathVariable Long id){
        try {
            Venta venta = ventaService.obtenerVentaId(id);
            if (venta != null) {
                return new ResponseEntity<>(venta, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Venta no encontrada con ID: " + id, HttpStatus.NOT_FOUND);
            }
            }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?> obtenerVentas(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "10" )int tamanio){
        try {
            return new ResponseEntity<>(ventaService.obtenerVentas(pagina, tamanio), HttpStatus.OK);
            }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarVenta(@PathVariable Long id){
        try {
            boolean eliminado = ventaService.eliminarVenta(id);
            if (eliminado) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Venta no encontrada con ID: " + id, HttpStatus.NOT_FOUND);
            }
            }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarVenta(@PathVariable Long id, @RequestBody Venta datosCambiar){
        try {
            Venta ventaActualizada = ventaService.actualizarVenta(id, datosCambiar);
            if (ventaActualizada != null) {
                return new ResponseEntity<>(ventaActualizada, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Venta no encontrada con ID: " + id, HttpStatus.NOT_FOUND);
            }
            }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
