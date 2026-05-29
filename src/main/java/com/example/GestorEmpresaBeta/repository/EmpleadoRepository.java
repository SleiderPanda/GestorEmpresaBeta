package com.example.GestorEmpresaBeta.repository;

import com.example.GestorEmpresaBeta.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
