package com.infnet.aquaflow.infraestrutura.persistencia.repositorio;

import com.infnet.aquaflow.infraestrutura.persistencia.entidade.AlunoJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaAlunoRepositorio extends JpaRepository<AlunoJpa, UUID> {
}
