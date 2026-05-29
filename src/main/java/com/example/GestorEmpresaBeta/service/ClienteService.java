package com.example.GestorEmpresaBeta.service;

import com.example.GestorEmpresaBeta.model.Cliente;
import com.example.GestorEmpresaBeta.repository.ClienteRepositoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClienteService {
    private final ClienteRepositoryRepository clienteRepositoryRepository;

    public Cliente guardarCliente(Cliente cliente){
        try {
            validarCliente(cliente);
            return clienteRepositoryRepository.save(cliente);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el cliente: " + e.getMessage(), e);
        }
    }

    public Cliente obtenerClienteId(Long id){
        try {
            if (id == null || id < 1) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            return clienteRepositoryRepository.findById(id).orElse(null);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el cliente por ID: " + e.getMessage(), e);
        }
    }

    public Page<Cliente> obtenerClientes(int pagina, int tamanio){
        try {
            Pageable pageable = PageRequest.of(pagina, tamanio);
            return clienteRepositoryRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de clientes: " + e.getMessage(), e);
        }
    }

    public boolean eliminarCliente(Long id){
        try {
            if (id == null || id < 1) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            if (!clienteRepositoryRepository.existsById(id)) return false;
            clienteRepositoryRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el cliente: " + e.getMessage(), e);
        }
    }

    public Cliente actualizarCliente(Long id, Cliente datosCambiar){
        try {
            if (id == null || id < 1) throw new IllegalArgumentException("El id no puede ser nulo o menor a 1");
            Cliente existente = clienteRepositoryRepository.findById(id).orElse(null);
            if (existente == null) return null;
            validarCliente(datosCambiar);
            existente.setNombre(datosCambiar.getNombre());
            existente.setApellido(datosCambiar.getApellido());
            existente.setDocumento(datosCambiar.getDocumento());
            existente.setCorreoElectronico(datosCambiar.getCorreoElectronico());
            existente.setTelefono(datosCambiar.getTelefono());
            return clienteRepositoryRepository.save(existente);
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
