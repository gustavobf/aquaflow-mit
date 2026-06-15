package com.infnet.aquaflow.dominio.entidade;

import com.infnet.aquaflow.dominio.enumeracao.StatusAluno;

import java.util.UUID;

public record Aluno(UUID id, String nome, String email, StatusAluno status) {
    public static Aluno novo (String nome, String email) {
        return new Aluno(UUID.randomUUID(), nome, email, StatusAluno.ATIVO);
    }

    public Aluno atualizar (String novoNome, String novoEmail) {
        return new Aluno(id, novoNome, novoEmail, status);
    }

    public Aluno inativar () {
        return new Aluno(id, nome, email, StatusAluno.INATIVO);
    }
}
