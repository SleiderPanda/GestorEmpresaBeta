package com.example.GestorEmpresaBeta.service;

import com.example.GestorEmpresaBeta.model.Gasto;
import com.example.GestorEmpresaBeta.repository.GastoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class GastoService {
    private final GastoRepository gastoRepository;
    public Gasto guardarGasto(Gasto gasto){
        try {
            validarGasto(gasto);
            gasto.setFecha(java.time.LocalDateTime.now());
            return gastoRepository.save(gasto);
        }  catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el gasto: " + e.getMessage(), e);
        }
    }
    public Gasto obtenerGastoId(Long id){
        try {
            if (id == null || id < 1) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            return gastoRepository.findById(id).orElse(null);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException("Error al obtener el gasto por ID: " + e.getMessage(), e);
        }
    }
    public Page<Gasto> obtenerGastos(int pagina, int tamanio){
        try {
            Pageable pageable = PageRequest.of(pagina,tamanio);
            return gastoRepository.findAll(pageable);
        }catch (Exception e){
            throw new RuntimeException("Error al obtener la lista de gastos: " + e.getMessage(), e);
        }
    }
    public boolean eliminarGasto(Long id){
        try {
            if (id == null || id < 1) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            if (!gastoRepository.existsById(id)) return false;
            gastoRepository.deleteById(id);
            return true;
        }catch (IllegalArgumentException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el gasto: " + e.getMessage(), e);
        }
    }
    public Gasto actualizarGasto(Long id, Gasto datosCambiar){
        try {
            if (id == null || id < 1) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            Gasto existente = gastoRepository.findById(id).orElse(null);
            if (existente == null) return null;
            validarGasto(datosCambiar);
            existente.setDescripcion(datosCambiar.getDescripcion());
            existente.setMonto(datosCambiar.getMonto());
            existente.setFecha(datosCambiar.getFecha());
            return gastoRepository.save(existente);
        }catch (IllegalArgumentException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el gasto: " + e.getMessage(), e);
        }
    }
    public void validarGasto(Gasto gasto){
        if (gasto.getDescripcion() == null || gasto.getDescripcion().isBlank())
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        if (gasto.getMonto() == null || gasto.getMonto() <= 0)
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
    }
}
