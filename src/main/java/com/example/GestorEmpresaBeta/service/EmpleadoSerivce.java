package com.example.GestorEmpresaBeta.service;

import com.example.GestorEmpresaBeta.model.Empleado;
import com.example.GestorEmpresaBeta.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class EmpleadoSerivce {
    private final EmpleadoRepository empleadoRepository;
    public Empleado guardarEmpleado(Empleado empleado){
        try {
            if (empleado.getNombre() == null || empleado.getNombre().isBlank())
                throw new IllegalArgumentException("El nombre no puede estar vacío");
            if (empleado.getApellido() == null || empleado.getApellido().isBlank())
                throw new IllegalArgumentException("El apellido no puede estar vacío");
            if (empleado.getDocumento() == null || empleado.getDocumento().isBlank())
                throw new IllegalArgumentException("El documento no puede estar vacío");
            if (empleado.getCargo() == null || empleado.getCargo().isBlank())
                throw new IllegalArgumentException("El cargo no puede estar vacío");
            if (empleado.getCorreoElectronico() == null || empleado.getCorreoElectronico().isBlank())
                throw new IllegalArgumentException("El correo electrónico no puede estar vacío");
            return empleadoRepository.save(empleado);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el empleado: " + e.getMessage(), e);
        }
    }
    public Empleado obtenerEmpleadoId(Long id) {
        try {
            if (id == null || id <= 0) throw new IllegalArgumentException("El id no puede ser nulo o negativo");
            return empleadoRepository.findById(id).orElse(null);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el empleado por ID: " + e.getMessage(), e);
        }
    }
    public Page<Empleado> obtenerEmpleados(int pagina, int tamanio){
        try {
            Pageable pageable = PageRequest.of(pagina, tamanio);
            return empleadoRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de empleados: " + e.getMessage(), e);
        }
    }
    public boolean eliminarEmpleado(Long id){
        try {
            if (id == null || id <= 0) throw new IllegalArgumentException("El id no puede ser nulo o negativo");
            if (!empleadoRepository.existsById(id)) return false;
            empleadoRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el empleado: " + e.getMessage(), e);
        }
    }
    public Empleado actualizarEmpleado(Long id, Empleado datosCambiar){
        try {
            Empleado existente = empleadoRepository.findById(id).orElse(null);
            if (existente == null) return null;
            if (datosCambiar.getNombre() == null || datosCambiar.getNombre().isBlank())
                throw new IllegalArgumentException("El nombre no puede estar vacío");
            if (datosCambiar.getApellido() == null || datosCambiar.getApellido().isBlank())
                throw new IllegalArgumentException("El apellido no puede estar vacío");
            if (datosCambiar.getDocumento() == null || datosCambiar.getDocumento().isBlank())
                throw new IllegalArgumentException("El documento no puede estar vacío");
            if (datosCambiar.getCargo() == null || datosCambiar.getCargo().isBlank())
                throw new IllegalArgumentException("El cargo no puede estar vacío");
            if (datosCambiar.getCorreoElectronico() == null || datosCambiar.getCorreoElectronico().isBlank())
                throw new IllegalArgumentException("El correo electrónico no puede estar vacío");
            existente.setNombre(datosCambiar.getNombre());
            existente.setApellido(datosCambiar.getApellido());
            existente.setDocumento(datosCambiar.getDocumento());
            existente.setCargo(datosCambiar.getCargo());
            existente.setCorreoElectronico(datosCambiar.getCorreoElectronico());
            return empleadoRepository.save(existente);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el empleado: " + e.getMessage(), e);
        }
    }
}
