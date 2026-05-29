package com.example.GestorEmpresaBeta.controller;

import com.example.GestorEmpresaBeta.model.Empleado;
import com.example.GestorEmpresaBeta.service.EmpleadoSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController @RequestMapping("/api/empleados")
public class EmpleadoController {
    private final EmpleadoSerivce empleadoSerivce;
    @PostMapping
    public ResponseEntity<Empleado> guardarEmpleado(@RequestBody Empleado empleado) {
        try {
            return new ResponseEntity<>(empleadoSerivce.guardarEmpleado(empleado), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoId(@PathVariable Long id) {
        try {
            Empleado empleado = empleadoSerivce.obtenerEmpleadoId(id);
            if (empleado != null) {
                return new ResponseEntity<>(empleado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<Page<Empleado>> obtenerEmpleados(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "10") int tamanio){
        try {
            return new ResponseEntity<>(empleadoSerivce.obtenerEmpleados(pagina, tamanio), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarEmpleado(@PathVariable Long id) {
        try {
            boolean eliminado = empleadoSerivce.eliminarEmpleado(id);
            if (eliminado) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado datosCambiar) {
        try {
            Empleado empleadoActualizado = empleadoSerivce.actualizarEmpleado(id, datosCambiar);
            if (empleadoActualizado != null) {
                return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
