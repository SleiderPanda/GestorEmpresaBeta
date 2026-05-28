// ProductoController.java
package com.example.GestorEmpresaBeta.controller;

import com.example.GestorEmpresaBeta.model.Producto;
import com.example.GestorEmpresaBeta.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto) {
        return new ResponseEntity<>(productoService.guardarProducto(producto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoId(@PathVariable Long id) {
        return new ResponseEntity<>(productoService.obtenerProductoId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerProductos() {
        return new ResponseEntity<>(productoService.obtenerProductos(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarProducto(@PathVariable Long id) {
        return new ResponseEntity<>(productoService.eliminarProducto(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto datosCambiar) {
        return new ResponseEntity<>(productoService.actualizarProducto(id, datosCambiar), HttpStatus.OK);
    }
}