// CategoriaProductoController.java
package com.example.GestorEmpresaBeta.controller;

import com.example.GestorEmpresaBeta.model.CategoriaProducto;
import com.example.GestorEmpresaBeta.service.CategoriaProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categorias")
public class CategoriaProductoController {

    private final CategoriaProductoService categoriaProductoService;

    @PostMapping
    public ResponseEntity<CategoriaProducto> guardarCategoria(@RequestBody CategoriaProducto categoriaProducto) {
        return new ResponseEntity<>(categoriaProductoService.guardarCategoria(categoriaProducto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProducto> obtenerCategoriaId(@PathVariable Long id) {
        return new ResponseEntity<>(categoriaProductoService.obtenerCategoriaId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProducto>> obtenerCategorias() {
        return new ResponseEntity<>(categoriaProductoService.obtenerCategorias(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarCategoria(@PathVariable Long id) {
        return new ResponseEntity<>(categoriaProductoService.eliminarCategoria(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProducto> actualizarCategoria(@PathVariable Long id, @RequestBody CategoriaProducto datosCambiar) {
        return new ResponseEntity<>(categoriaProductoService.actualizarCategoria(id, datosCambiar), HttpStatus.OK);
    }
}