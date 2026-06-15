package com.infnet.aquaflow.infraestrutura.persistencia.repositorio;

import com.infnet.aquaflow.infraestrutura.persistencia.entidade.MatriculaJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaMatriculaRepositorio extends JpaRepository<MatriculaJpa, UUID> {
    List<MatriculaJpa> findByAlunoId (UUID alunoId);

    List<MatriculaJpa> findByTurmaId (UUID turmaId);
}
