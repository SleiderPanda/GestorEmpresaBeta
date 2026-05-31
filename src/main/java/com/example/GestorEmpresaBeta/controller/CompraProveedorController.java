package com.example.GestorEmpresaBeta.controller;

import com.example.GestorEmpresaBeta.model.CompraProveedor;
import com.example.GestorEmpresaBeta.service.CompraProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/compras")
public class CompraProveedorController {
    private final CompraProveedorService compraProveedorService;
    @PostMapping
    public ResponseEntity<?> guardarCompra(@RequestBody CompraProveedor compra) {
        try {
            return new  ResponseEntity<>(compraProveedorService.guardarCompra(compra), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCompraProveedorId(@PathVariable Long id) {
        try {
            CompraProveedor compra = compraProveedorService.obtenerCompraProveedorId(id);
            if (compra != null) {
                return new ResponseEntity<>(compra,HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Compra de Proveedor no encontrada con ID: " + id, HttpStatus.NOT_FOUND);
            }
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?> obtenerCompras(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "10") int tamanio) {
        try {
            return new ResponseEntity<>(compraProveedorService.obtenerCompras(pagina, tamanio), HttpStatus.OK);
            } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCompraProveedor(@PathVariable Long id) {
        try {
            boolean eliminado = compraProveedorService.eliminarCompraProveedor(id);
            if (eliminado) {
                return new  ResponseEntity<>(true,HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Compra de Proveedor no encontrada con ID: " + id, HttpStatus.NOT_FOUND);
            }
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCompraProveedor(@PathVariable Long id, @RequestBody CompraProveedor datosCambiar) {
        try {
            CompraProveedor compraProveedor = compraProveedorService.actualizarCompraProveedor(id,datosCambiar);
            if (compraProveedor != null) {
                return new ResponseEntity<>(compraProveedor, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Compra de Proveedor no encontrada con ID: " + id, HttpStatus.NOT_FOUND);
            }
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }
    }

