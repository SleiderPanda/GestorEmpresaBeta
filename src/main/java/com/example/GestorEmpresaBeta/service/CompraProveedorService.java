package com.example.GestorEmpresaBeta.service;

import com.example.GestorEmpresaBeta.model.CompraProveedor;
import com.example.GestorEmpresaBeta.model.DetalleCompra;
import com.example.GestorEmpresaBeta.model.Producto;
import com.example.GestorEmpresaBeta.model.Proveedor;
import com.example.GestorEmpresaBeta.repository.CompraProveedorRepository;
import com.example.GestorEmpresaBeta.repository.ProductoRepository;
import com.example.GestorEmpresaBeta.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CompraProveedorService {
    private final CompraProveedorRepository compraProveedorRepository;
    private final ProveedorRepository proveedorRepository;
    private final ProductoRepository productoRepository;
    //API para guardar compras calcular el subtotal de cada detalle
    // suma el total de compra y suma el stock en tiempo real de cada producto
    public CompraProveedor guardarCompra(CompraProveedor compra) {
        try {
            validarCompra(compra);
            Proveedor proveedor = proveedorRepository.findById(compra.getProveedor().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));
            compra.setProveedor(proveedor);
            double total = 0;
            for (DetalleCompra detalle : compra.getDetalles()) {
                Producto producto = productoRepository.findById(detalle.getProducto().getIdProducto())
                        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
                detalle.setCompra(compra);
                detalle.setPrecioUnitario(producto.getPrecioProducto());
                detalle.setSubtotal(detalle.getCantidad() * detalle.getPrecioUnitario());
                total += detalle.getSubtotal();
                producto.setStockProducto(producto.getStockProducto() + detalle.getCantidad());
                productoRepository.save(producto);
                detalle.setProducto(producto);
            }
            compra.setTotal(total);
            compra.setFecha(LocalDateTime.now());
            CompraProveedor compraGuardada = compraProveedorRepository.save(compra);
            for (DetalleCompra detalle : compraGuardada.getDetalles()) {
                Producto productoCompleto = productoRepository.findById(detalle.getProducto().getIdProducto())
                        .orElse(null);
                detalle.setProducto(productoCompleto);
            }

            return compraGuardada;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la compra: " + e.getMessage(), e);
        }
    }
    public CompraProveedor obtenerCompraProveedorId(Long id){
        try {
            if (id == null || id < 0) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            return compraProveedorRepository.findById(id).orElse(null);
        } catch (IllegalArgumentException e) {
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Error al obtener la compra por ID: " + e.getMessage(), e);
        }
    }
    public Page<CompraProveedor> obtenerCompras(int pagina, int tamanio){
        try {
            Pageable pageable = PageRequest.of(pagina, tamanio);
            return compraProveedorRepository.findAll(pageable);
        }catch (Exception e){
            throw new RuntimeException("Error al obtener la lista de ventas: " + e.getMessage(), e);
        }
    }
    public boolean eliminarCompraProveedor(Long id){
        try {
            if (id == null || id < 0) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            if (!compraProveedorRepository.existsById(id)) return false;
            compraProveedorRepository.deleteById(id);
            return true;
        }catch (IllegalArgumentException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar la compra: " + e.getMessage(), e);
        }
    }
    public CompraProveedor actualizarCompraProveedor(Long id, CompraProveedor datosCambiar){
        try {
            if (id == null || id < 0) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            CompraProveedor existente = compraProveedorRepository.findById(id).orElse(null);
            if (existente == null) return null;
            validarCompra(datosCambiar);
            existente.setProveedor(datosCambiar.getProveedor());
            existente.setTotal(datosCambiar.getTotal());
            existente.setEstado(datosCambiar.getEstado());
            return compraProveedorRepository.save(existente);
        }catch (IllegalArgumentException e){
            throw e;
        }
    }
    public void validarCompra(CompraProveedor compra) {
        if (compra.getProveedor() == null)
            throw new IllegalArgumentException("El proveedor no puede estar vacío");
        if (compra.getDetalles() == null || compra.getDetalles().isEmpty())
            throw new IllegalArgumentException("La compra debe tener al menos un producto");
        if (compra.getEstado() == null || compra.getEstado().isBlank())
            throw new IllegalArgumentException("El estado no puede estar vacío");
    }

}


