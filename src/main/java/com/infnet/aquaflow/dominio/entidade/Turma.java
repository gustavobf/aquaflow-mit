package com.infnet.aquaflow.dominio.entidade;

import com.infnet.aquaflow.dominio.enumeracao.NivelTurma;

import java.util.UUID;

public record Turma(UUID id, String nome, NivelTurma nivel, UUID professorId, int capacidadeMaxima) {
    public static Turma nova (String nome, NivelTurma nivel, UUID professorId, int capacidadeMaxima) {
        return new Turma(UUID.randomUUID(), nome, nivel, professorId, capacidadeMaxima);
    }

    public Turma atualizar (String novoNome, NivelTurma novoNivel, UUID novoProfessorId, int novaCapacidade) {
        return new Turma(id, novoNome, novoNivel, novoProfessorId, novaCapacidade);
    }
}
