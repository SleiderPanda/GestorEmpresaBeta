package com.example.GestorEmpresaBeta.repository;

import com.example.GestorEmpresaBeta.model.CompraProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraProveedorRepository extends JpaRepository<CompraProveedor, Long>{
}
