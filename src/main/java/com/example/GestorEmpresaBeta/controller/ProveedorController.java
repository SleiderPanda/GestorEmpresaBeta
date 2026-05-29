package com.example.GestorEmpresaBeta.controller;

import com.example.GestorEmpresaBeta.model.Proveedor;
import com.example.GestorEmpresaBeta.service.ProductoService;
import com.example.GestorEmpresaBeta.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController @RequestMapping("/api/proveedores")
public class ProveedorController {
    private final ProveedorService proveedorService;
    @PostMapping
    public ResponseEntity<Proveedor> guardarProveedor(@RequestBody Proveedor proveedor){
        try {
            return new ResponseEntity<>(proveedorService.guardarProveedor(proveedor), HttpStatus.CREATED);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerProveedorId(@PathVariable Long id){
        try {
            Proveedor proveedor = proveedorService.obtenerProveedorId(id);
            if (proveedor != null) {
                return new ResponseEntity<>(proveedor, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<Page<Proveedor>> obtenerProveedores(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "10") int tamanio){
        try {
            return new ResponseEntity<>(proveedorService.obtenerProveedores(pagina, tamanio), HttpStatus.OK);
            }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarProveedor(@PathVariable Long id){
        try {
            boolean eliminado = proveedorService.eliminarProveedor(id);
            if (eliminado) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
            }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor datosCambiar){
        try {
            Proveedor proveedorActualizado =proveedorService.actualizarProveedor(id, datosCambiar);
            if (proveedorActualizado != null) {
                return new ResponseEntity<>(proveedorActualizado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
