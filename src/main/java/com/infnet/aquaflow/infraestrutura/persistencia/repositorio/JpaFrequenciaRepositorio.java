package com.infnet.aquaflow.infraestrutura.persistencia.repositorio;

import com.infnet.aquaflow.infraestrutura.persistencia.entidade.FrequenciaJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaFrequenciaRepositorio extends JpaRepository<FrequenciaJpa, UUID> {
    List<FrequenciaJpa> findByMatriculaIdOrderByDataAulaDesc (UUID matriculaId);
}
