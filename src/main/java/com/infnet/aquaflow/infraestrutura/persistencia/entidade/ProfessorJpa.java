package com.infnet.aquaflow.infraestrutura.persistencia.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "professores")
public class ProfessorJpa {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String especialidade;

    protected ProfessorJpa () {
    }

    public ProfessorJpa (UUID id, String nome, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public UUID getId () {
        return id;
    }

    public String getNome () {
        return nome;
    }

    public String getEspecialidade () {
        return especialidade;
    }
}
