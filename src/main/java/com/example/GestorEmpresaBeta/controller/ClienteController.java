package com.example.GestorEmpresaBeta.controller;

import com.example.GestorEmpresaBeta.model.Cliente;
import com.example.GestorEmpresaBeta.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController @RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;
    @PostMapping
    public ResponseEntity<?> guardarCliente(@RequestBody Cliente cliente) {
        try {
            return new ResponseEntity<>(clienteService.guardarCliente(cliente), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerClienteId(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.obtenerClienteId(id);
            if (cliente != null) {
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cliente no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?> obtenerClientes(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "10") int tamanio) {
        try {
            return new ResponseEntity<>(clienteService.obtenerClientes(pagina, tamanio), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
        try {
            boolean eliminado = clienteService.eliminarCliente(id);
            if (eliminado) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cliente no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Long id, @RequestBody Cliente datosCambiar) {
        try {
            Cliente clienteActualizado = clienteService.actualizarCliente(id, datosCambiar);
            if (clienteActualizado != null) {
                return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cliente no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
