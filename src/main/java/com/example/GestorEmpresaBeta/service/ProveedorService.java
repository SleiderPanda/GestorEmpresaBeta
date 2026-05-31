package com.example.GestorEmpresaBeta.service;

import com.example.GestorEmpresaBeta.model.Proveedor;
import com.example.GestorEmpresaBeta.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProveedorService {
    private final ProveedorRepository proveedorRepository;

    public Proveedor guardarProveedor(Proveedor proveedor) {
        try {
            validarProveedor(proveedor);
            return proveedorRepository.save(proveedor);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el cliente: " + e.getMessage(), e);
        }
    }

    public Proveedor obtenerProveedorId(Long id) {
        try {
            if (id == null || id < 1) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            return proveedorRepository.findById(id).orElse(null);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el cliente por ID: " + e.getMessage(), e);
        }
    }

    public Page<Proveedor> obtenerProveedores(int pagina, int tamanio) {
        try {
            Pageable pageable = PageRequest.of(pagina, tamanio);
            return proveedorRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de clientes: " + e.getMessage(), e);
        }
    }

    public boolean eliminarProveedor(Long id) {
        try {
            if (id == null || id < 1) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            if (!proveedorRepository.existsById(id)) return false;
            proveedorRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el cliente por ID: " + e.getMessage(), e);
        }
    }

    public Proveedor actualizarProveedor(Long id, Proveedor datosCambiar) {
        try {
            if (id == null || id < 1) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            Proveedor existente = proveedorRepository.findById(id).orElse(null);
            if (existente == null) return null;
            validarProveedor(datosCambiar);
            existente.setNombre(datosCambiar.getNombre());
            existente.setDocumento(datosCambiar.getDocumento());
            existente.setCorreoElectronico(datosCambiar.getCorreoElectronico());
            existente.setTelefono(datosCambiar.getTelefono());
            return proveedorRepository.save(existente);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el cliente: " + e.getMessage(), e);
        }
    }

    public void validarProveedor(Proveedor proveedor) {
        if (proveedor.getNombre() == null || proveedor.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (!proveedor.getNombre().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"))
            throw new IllegalArgumentException("El nombre solo puede contener letras");
        if (proveedor.getDocumento() == null || proveedor.getDocumento().isBlank())
            throw new IllegalArgumentException("El documento no puede estar vacio");
        if (!proveedor.getDocumento().matches("\\d+"))
            throw new IllegalArgumentException("El documento debe tener dígitos");
        if (proveedor.getCorreoElectronico() == null || proveedor.getCorreoElectronico().isBlank())
            throw new IllegalArgumentException("El correo electronico no puede estar vacio");
        if (!proveedor.getCorreoElectronico().matches("^[A-Za-z0-9._%+-]+@(gmail|hotmail|outlook)\\.com$"))
            throw new IllegalArgumentException("Correo electrónico inválido");
        if (proveedor.getTelefono() == null || proveedor.getTelefono().isBlank())
            throw new IllegalArgumentException("El telefono no puede estar vacio");
        if (!proveedor.getTelefono().matches("\\d{10}"))
            throw new IllegalArgumentException("El teléfono debe tener exactamente 10 dígitos");
    }
}
