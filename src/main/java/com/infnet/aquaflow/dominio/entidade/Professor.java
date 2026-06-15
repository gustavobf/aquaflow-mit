package com.infnet.aquaflow.dominio.entidade;

import java.util.UUID;

public record Professor(UUID id, String nome, String especialidade) {
    public static Professor novo (String nome, String especialidade) {
        return new Professor(UUID.randomUUID(), nome, especialidade);
    }

    public Professor atualizar (String novoNome, String novaEspecialidade) {
        return new Professor(id, novoNome, novaEspecialidade);
    }
}
