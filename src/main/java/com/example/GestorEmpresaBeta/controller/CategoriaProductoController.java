// CategoriaProductoController.java
package com.example.GestorEmpresaBeta.controller;

import com.example.GestorEmpresaBeta.model.CategoriaProducto;
import com.example.GestorEmpresaBeta.service.CategoriaProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categorias")
public class CategoriaProductoController {

    private final CategoriaProductoService categoriaProductoService;

    @PostMapping
    public ResponseEntity<?> guardarCategoria(@RequestBody CategoriaProducto categoriaProducto) {
        try {
            return new ResponseEntity<>(categoriaProductoService.guardarCategoria(categoriaProducto), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCategoriaId(@PathVariable Long id) {
        try {
            CategoriaProducto categoriaProducto = categoriaProductoService.obtenerCategoriaId(id);
            if (categoriaProducto != null) {
                return new ResponseEntity<>(categoriaProducto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Categoría de Producto no encontrada con ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerCategorias(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "10") int tamanio) {
        try {
            return new ResponseEntity<>(categoriaProductoService.obtenerCategorias(pagina, tamanio), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id) {
        try {
            boolean eliminado = categoriaProductoService.eliminarCategoria(id);
            if (eliminado) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Categoría de Producto no encontrada con ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCategoria(@PathVariable Long id, @RequestBody CategoriaProducto datosCambiar) {
        try {
            CategoriaProducto categoriaProductoActualizada = categoriaProductoService.actualizarCategoria(id, datosCambiar);
            if (categoriaProductoActualizada != null) {
                return new ResponseEntity<>(categoriaProductoActualizada, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Categoría de Producto no encontrada con ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
