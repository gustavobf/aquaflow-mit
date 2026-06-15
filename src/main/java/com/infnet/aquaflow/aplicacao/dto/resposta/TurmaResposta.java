package com.infnet.aquaflow.aplicacao.dto.resposta;

import com.infnet.aquaflow.dominio.enumeracao.NivelTurma;

import java.util.UUID;

public record TurmaResposta(UUID id, String nome, NivelTurma nivel, UUID professorId, int capacidadeMaxima) {
}
