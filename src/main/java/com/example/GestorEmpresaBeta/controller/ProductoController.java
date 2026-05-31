// ProductoController.java
package com.example.GestorEmpresaBeta.controller;

import com.example.GestorEmpresaBeta.model.Producto;
import com.example.GestorEmpresaBeta.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController @RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<?> guardarProducto(@RequestBody Producto producto) {
        try {
            return new ResponseEntity<>(productoService.guardarProducto(producto), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoId(@PathVariable Long id) {
        try {
            Producto producto = productoService.obtenerProductoId(id);
            if (producto != null) {
                return new ResponseEntity<>(producto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Producto no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerProductos(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "10") int tamanio) {
        try {
            return new ResponseEntity<>(productoService.obtenerProductos(pagina, tamanio), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        try {
            boolean eliminado = productoService.eliminarProducto(id);
            if (eliminado) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Producto no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto datosCambiar) {
        try {
            Producto productoActualizado = productoService.actualizarProducto(id, datosCambiar);
            if (productoActualizado != null) {
                return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Producto no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
