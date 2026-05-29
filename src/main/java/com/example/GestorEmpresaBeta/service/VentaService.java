package com.example.GestorEmpresaBeta.service;

import com.example.GestorEmpresaBeta.model.Cliente;
import com.example.GestorEmpresaBeta.model.DetalleVenta;
import com.example.GestorEmpresaBeta.model.Producto;
import com.example.GestorEmpresaBeta.model.Venta;
import com.example.GestorEmpresaBeta.repository.ClienteRepository;
import com.example.GestorEmpresaBeta.repository.ProductoRepository;
import com.example.GestorEmpresaBeta.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class VentaService {
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;
    //API para guardar venta calcular el subtotal  de cada detalle
    // suma el total de venta y descuenta el stock en tiempo real de cada producto
    public Venta guardarVenta(Venta venta){
        try {
            validarVenta(venta);
            double total = 0;
            for (DetalleVenta detalle : venta.getDetalles()) {
                Producto producto = productoRepository.findById(detalle.getProducto().getIdProducto())
                        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
                detalle.setVenta(venta);
                detalle.setPrecioUnitario(producto.getPrecioProducto());
                detalle.setSubtotal(detalle.getCantidad() * detalle.getPrecioUnitario());
                total += detalle.getSubtotal();
                producto.setStockProducto(producto.getStockProducto() - detalle.getCantidad());
                productoRepository.save(producto);
            }
            venta.setTotal(total);
            venta.setFecha(LocalDateTime.now());
            Cliente clienteCompleto = clienteRepository.findById(venta.getCliente().getIdCliente())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
            venta.setCliente(clienteCompleto);
            Venta ventaGuardada = ventaRepository.save(venta);
            for (DetalleVenta detalle : ventaGuardada.getDetalles()) {
                Producto productoCompleto = productoRepository.findById(detalle.getProducto().getIdProducto())
                        .orElse(null);
                detalle.setProducto(productoCompleto);
            }
            return ventaGuardada;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la venta: " + e.getMessage(), e);
        }
    }
    public Venta obtenerVentaId(Long id){
        try {
            if (id == null || id <= 0) throw new IllegalArgumentException("El id no puede ser nulo o negativo");
            return ventaRepository.findById(id).orElse(null);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException("Error al obtener la venta por ID: " + e.getMessage(), e);
        }
    }
    public Page<Venta> obtenerVentas(int pagina, int tamanio){
        try {
            Pageable pageable = PageRequest.of(pagina,tamanio);
            return ventaRepository.findAll(pageable);
        }catch (Exception e){
            throw new RuntimeException("Error al obtener la lista de ventas: " + e.getMessage(), e);
        }
    }
    public boolean eliminarVenta(Long id){
        try {
            if (id == null || id <= 0) throw new IllegalArgumentException("El id no puede ser nulo o negativo");
            if (!ventaRepository.existsById(id)) return false;
            ventaRepository.deleteById(id);
            return true;
            } catch (IllegalArgumentException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException("Error al eliminar la venta: " + e.getMessage(), e);
        }
    }
    public Venta actualizarVenta(Long id, Venta datosCambiar){
        try {
            if (id == null || id <= 0) throw new IllegalArgumentException("El id no puede ser nulo o negativo");
            Venta existente = ventaRepository.findById(id).orElse(null);
            if (existente == null) return null;
            validarVenta(datosCambiar);
            existente.setCliente(datosCambiar.getCliente());
            existente.setTotal(datosCambiar.getTotal());
            existente.setEstado(datosCambiar.getEstado());
            return ventaRepository.save(existente);
        } catch (IllegalArgumentException e) {
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar la venta: " + e.getMessage(), e);
        }
    }
    public void validarVenta(Venta venta){
        if (venta.getCliente() == null)
            throw new IllegalArgumentException("El cliente no puede estar vacío");
        if (venta.getEstado() == null)
            throw new IllegalArgumentException("El estado no puede estar vacío");
    }
}
