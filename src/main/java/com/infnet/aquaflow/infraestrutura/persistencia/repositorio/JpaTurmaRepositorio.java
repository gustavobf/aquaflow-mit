package com.infnet.aquaflow.infraestrutura.persistencia.repositorio;

import com.infnet.aquaflow.infraestrutura.persistencia.entidade.TurmaJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaTurmaRepositorio extends JpaRepository<TurmaJpa, UUID> {
}
