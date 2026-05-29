package com.example.GestorEmpresaBeta.repository;

import com.example.GestorEmpresaBeta.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositoryRepository extends JpaRepository<Cliente, Long> {
}
