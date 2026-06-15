package com.infnet.aquaflow.infraestrutura.persistencia.entidade;

import com.infnet.aquaflow.dominio.enumeracao.StatusAluno;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "alunos")
public class AlunoJpa {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAluno status;

    protected AlunoJpa () {
    }

    public AlunoJpa (UUID id, String nome, String email, StatusAluno status) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.status = status;
    }

    public UUID getId () {
        return id;
    }

    public String getNome () {
        return nome;
    }

    public String getEmail () {
        return email;
    }

    public StatusAluno getStatus () {
        return status;
    }
}
