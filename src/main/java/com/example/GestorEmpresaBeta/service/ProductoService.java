// ProductoService.java
package com.example.GestorEmpresaBeta.service;

import com.example.GestorEmpresaBeta.model.Producto;
import com.example.GestorEmpresaBeta.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public Producto guardarProducto(Producto producto) {
        if (producto.getNombreProducto() == null || producto.getNombreProducto().isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (producto.getPrecioProducto() == null || producto.getPrecioProducto() <= 0)
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        if (producto.getStockProducto() == null || producto.getStockProducto() <= 0)
            throw new IllegalArgumentException("El stock debe ser mayor a 0");
        if (producto.getCategoriaProducto() == null)
            throw new IllegalArgumentException("La categoría no puede estar vacía");
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoId(Long id) {
        if (id == null || id <= 0) return null;
        return productoRepository.findById(id).orElse(null);
    }

    public boolean eliminarProducto(Long id) {
        if (id == null || id <= 0) return false;
        if (!productoRepository.existsById(id)) return false;
        productoRepository.deleteById(id);
        return true;
    }

    public Producto actualizarProducto(Long id, Producto datosCambiar) {
        Producto existente = productoRepository.findById(id).orElse(null);
        if (existente == null) return null;
        if (datosCambiar.getNombreProducto() == null || datosCambiar.getNombreProducto().isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (datosCambiar.getPrecioProducto() == null || datosCambiar.getPrecioProducto() <= 0)
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        if (datosCambiar.getStockProducto() == null || datosCambiar.getStockProducto() <= 0)
            throw new IllegalArgumentException("El stock debe ser mayor a 0");
        if (datosCambiar.getCategoriaProducto() == null)
            throw new IllegalArgumentException("La categoría no puede estar vacía");
        existente.setNombreProducto(datosCambiar.getNombreProducto());
        existente.setPrecioProducto(datosCambiar.getPrecioProducto());
        existente.setStockProducto(datosCambiar.getStockProducto());
        existente.setDescripcionProducto(datosCambiar.getDescripcionProducto());
        existente.setCategoriaProducto(datosCambiar.getCategoriaProducto());
        return productoRepository.save(existente);
    }
}