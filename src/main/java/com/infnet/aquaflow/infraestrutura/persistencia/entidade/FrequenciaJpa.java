package com.infnet.aquaflow.infraestrutura.persistencia.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "frequencias")
public class FrequenciaJpa {
    @Id
    private UUID id;
    @Column(nullable = false)
    private UUID matriculaId;
    @Column(nullable = false)
    private LocalDate dataAula;
    @Column(nullable = false)
    private boolean presente;

    protected FrequenciaJpa () {
    }

    public FrequenciaJpa (UUID id, UUID matriculaId, LocalDate dataAula, boolean presente) {
        this.id = id;
        this.matriculaId = matriculaId;
        this.dataAula = dataAula;
        this.presente = presente;
    }

    public UUID getId () {
        return id;
    }

    public UUID getMatriculaId () {
        return matriculaId;
    }

    public LocalDate getDataAula () {
        return dataAula;
    }

    public boolean isPresente () {
        return presente;
    }
}
