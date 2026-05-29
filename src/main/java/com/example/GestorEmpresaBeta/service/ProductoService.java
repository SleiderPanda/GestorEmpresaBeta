package com.example.GestorEmpresaBeta.service;

import com.example.GestorEmpresaBeta.model.Producto;
import com.example.GestorEmpresaBeta.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public Producto guardarProducto(Producto producto) {
        try {
            validarProducto(producto);
            return productoRepository.save(producto);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el producto: " + e.getMessage(), e);
        }
    }

    public Page<Producto> obtenerProductos(int pagina, int tamanio){
        try {
            Pageable pageable = PageRequest.of(pagina, tamanio);
            return productoRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de productos: " + e.getMessage(), e);
        }
    }

    public Producto obtenerProductoId(Long id) {
        try {
            if (id == null || id < 1) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            return productoRepository.findById(id).orElse(null);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el producto por ID: " + e.getMessage(), e);
        }
    }

    public boolean eliminarProducto(Long id) {
        try {
            if (id == null || id < 1) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            if (!productoRepository.existsById(id)) return false;
            productoRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el producto: " + e.getMessage(), e);
        }
    }

    public Producto actualizarProducto(Long id, Producto datosCambiar) {
        try {
            if (id == null || id < 1) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            Producto existente = productoRepository.findById(id).orElse(null);
            if (existente == null) return null;
            validarProducto(datosCambiar);
            existente.setNombreProducto(datosCambiar.getNombreProducto());
            existente.setPrecioProducto(datosCambiar.getPrecioProducto());
            existente.setStockProducto(datosCambiar.getStockProducto());
            existente.setDescripcionProducto(datosCambiar.getDescripcionProducto());
            existente.setCategoriaProducto(datosCambiar.getCategoriaProducto());
            return productoRepository.save(existente);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el producto: " + e.getMessage(), e);
        }
    }

    private void validarProducto(Producto producto) {
        if (producto.getNombreProducto() == null || producto.getNombreProducto().isBlank())
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        if (producto.getPrecioProducto() == null || producto.getPrecioProducto() <= 0)
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        if (producto.getStockProducto() == null || producto.getStockProducto() < 0)
            throw new IllegalArgumentException("El stock no puede ser negativo");
        if (producto.getCategoriaProducto() == null)
            throw new IllegalArgumentException("La categoría no puede estar vacía");
    }
}
