package com.infnet.aquaflow.infraestrutura.persistencia.repositorio;

import com.infnet.aquaflow.infraestrutura.persistencia.entidade.ProfessorJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProfessorRepositorio extends JpaRepository<ProfessorJpa, UUID> {
}
