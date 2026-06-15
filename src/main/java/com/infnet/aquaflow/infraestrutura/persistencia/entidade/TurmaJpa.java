package com.infnet.aquaflow.infraestrutura.persistencia.entidade;

import com.infnet.aquaflow.dominio.enumeracao.NivelTurma;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "turmas")
public class TurmaJpa {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelTurma nivel;
    @Column(nullable = false)
    private UUID professorId;
    @Column(nullable = false)
    private int capacidadeMaxima;

    protected TurmaJpa () {
    }

    public TurmaJpa (UUID id, String nome, NivelTurma nivel, UUID professorId, int capacidadeMaxima) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
        this.professorId = professorId;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public UUID getId () {
        return id;
    }

    public String getNome () {
        return nome;
    }

    public NivelTurma getNivel () {
        return nivel;
    }

    public UUID getProfessorId () {
        return professorId;
    }

    public int getCapacidadeMaxima () {
        return capacidadeMaxima;
    }
}
