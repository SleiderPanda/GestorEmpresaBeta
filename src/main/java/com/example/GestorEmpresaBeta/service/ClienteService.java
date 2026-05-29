package com.example.GestorEmpresaBeta.service;

import com.example.GestorEmpresaBeta.model.Cliente;
import com.example.GestorEmpresaBeta.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public Cliente guardarCliente(Cliente cliente){
        try {
            validarCliente(cliente);
            return clienteRepository.save(cliente);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el cliente: " + e.getMessage(), e);
        }
    }

    public Cliente obtenerClienteId(Long id){
        try {
            if (id == null || id <= 0) throw new IllegalArgumentException("El id no puede ser nulo o negativo");
            return clienteRepository.findById(id).orElse(null);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el cliente por ID: " + e.getMessage(), e);
        }
    }

    //API nueva de obtener por documento
    public Cliente obtenerClienteDocumento(String documento){
        try {
            if (documento == null || documento.isBlank()) throw new IllegalArgumentException("El documento no puede estar vacío");
            return clienteRepository.findByDocumento(documento);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el cliente por documento: " + e.getMessage(), e);
        }
    }

    public Page<Cliente> obtenerClientes(int pagina, int tamanio){
        try {
            Pageable pageable = PageRequest.of(pagina, tamanio);
            return clienteRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de clientes: " + e.getMessage(), e);
        }
    }

    public boolean eliminarCliente(Long id){
        try {
            if (id == null || id <= 0) throw new IllegalArgumentException("El id no puede ser nulo o negativo");
            if (!clienteRepository.existsById(id)) return false;
            clienteRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el cliente: " + e.getMessage(), e);
        }
    }

    public Cliente actualizarCliente(Long id, Cliente datosCambiar){
        try {
            if (id == null || id <= 0) throw new IllegalArgumentException("El id no puede ser nulo o negativo");
            Cliente existente = clienteRepository.findById(id).orElse(null);
            if (existente == null) return null;
            validarCliente(datosCambiar);
            existente.setNombre(datosCambiar.getNombre());
            existente.setApellido(datosCambiar.getApellido());
            existente.setDocumento(datosCambiar.getDocumento());
            existente.setCorreoElectronico(datosCambiar.getCorreoElectronico());
            existente.setTelefono(datosCambiar.getTelefono());
            return clienteRepository.save(existente);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el cliente: " + e.getMessage(), e);
        }
    }

    private void validarCliente(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (cliente.getApellido() == null || cliente.getApellido().isBlank())
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        if (cliente.getDocumento() == null || cliente.getDocumento().isBlank())
            throw new IllegalArgumentException("El documento no puede estar vacío");
    }
}
