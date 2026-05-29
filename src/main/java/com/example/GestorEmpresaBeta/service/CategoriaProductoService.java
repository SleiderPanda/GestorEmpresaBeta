package com.example.GestorEmpresaBeta.service;

import com.example.GestorEmpresaBeta.model.CategoriaProducto;
import com.example.GestorEmpresaBeta.repository.CategoriaProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoriaProductoService {

    private final CategoriaProductoRepository categoriaProductoRepository;

    public CategoriaProducto guardarCategoria(CategoriaProducto categoriaProducto) {
        try {
            validarCategoria(categoriaProducto);
            return categoriaProductoRepository.save(categoriaProducto);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la categoría de producto: " + e.getMessage(), e);
        }
    }

    public CategoriaProducto obtenerCategoriaId(Long id) {
        try {
            if (id == null || id < 0) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            return categoriaProductoRepository.findById(id).orElse(null);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la categoría de producto por ID: " + e.getMessage(), e);
        }
    }

    public Page<CategoriaProducto> obtenerCategorias(int pagina, int tamanio) {
        try {
            Pageable pageable = PageRequest.of(pagina, tamanio);
            return categoriaProductoRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de categorías de producto: " + e.getMessage(), e);
        }
    }

    public boolean eliminarCategoria(Long id) {
        try {
            if (id == null || id < 0) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            if (!categoriaProductoRepository.existsById(id)) return false;
            categoriaProductoRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la categoría de producto: " + e.getMessage(), e);
        }
    }

    public CategoriaProducto actualizarCategoria(Long id, CategoriaProducto datosCambiar) {
        try {
            if (id == null || id < 0) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            CategoriaProducto existente = categoriaProductoRepository.findById(id).orElse(null);
            if (existente == null) return null;
            validarCategoria(datosCambiar);
            existente.setNombre(datosCambiar.getNombre());
            existente.setDescripcion(datosCambiar.getDescripcion());
            return categoriaProductoRepository.save(existente);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la categoría de producto: " + e.getMessage(), e);
        }
    }

    private void validarCategoria(CategoriaProducto categoriaProducto) {
        if (categoriaProducto.getNombre() == null || categoriaProducto.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (categoriaProducto.getDescripcion() == null || categoriaProducto.getDescripcion().isBlank())
            throw new IllegalArgumentException("La descripción no puede estar vacía");
    }
}
