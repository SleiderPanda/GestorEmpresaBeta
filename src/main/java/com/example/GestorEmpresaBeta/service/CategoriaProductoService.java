// CategoriaProductoService.java
package com.example.GestorEmpresaBeta.service;

import com.example.GestorEmpresaBeta.model.CategoriaProducto;
import com.example.GestorEmpresaBeta.repository.CategoriaProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoriaProductoService {

    private final CategoriaProductoRepository categoriaProductoRepository;

    public CategoriaProducto guardarCategoria(CategoriaProducto categoriaProducto) {
        if (categoriaProducto.getNombre() == null || categoriaProducto.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (categoriaProducto.getDescripcion() == null || categoriaProducto.getDescripcion().isBlank())
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        return categoriaProductoRepository.save(categoriaProducto);
    }

    public CategoriaProducto obtenerCategoriaId(Long id) {
        if (id == null || id <= 0) return null;
        return categoriaProductoRepository.findById(id).orElse(null);
    }

    public List<CategoriaProducto> obtenerCategorias() {
        return categoriaProductoRepository.findAll();
    }

    public boolean eliminarCategoria(Long id) {
        if (id == null || id <= 0) return false;
        if (!categoriaProductoRepository.existsById(id)) return false;
        categoriaProductoRepository.deleteById(id);
        return true;
    }

    public CategoriaProducto actualizarCategoria(Long id, CategoriaProducto datosCambiar) {
        CategoriaProducto existente = categoriaProductoRepository.findById(id).orElse(null);
        if (existente == null) return null;
        if (datosCambiar.getNombre() == null || datosCambiar.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (datosCambiar.getDescripcion() == null || datosCambiar.getDescripcion().isBlank())
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        existente.setNombre(datosCambiar.getNombre());
        existente.setDescripcion(datosCambiar.getDescripcion());
        return categoriaProductoRepository.save(existente);
    }
}