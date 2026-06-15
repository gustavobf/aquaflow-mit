package com.infnet.aquaflow.aplicacao.dto.resposta;

import com.infnet.aquaflow.dominio.enumeracao.StatusAluno;

import java.util.UUID;

public record AlunoResposta(UUID id, String nome, String email, StatusAluno status) {
}
