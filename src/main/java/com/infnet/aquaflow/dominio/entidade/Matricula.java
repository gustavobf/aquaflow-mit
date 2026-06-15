package com.infnet.aquaflow.dominio.entidade;

import com.infnet.aquaflow.dominio.enumeracao.StatusMatricula;

import java.math.BigDecimal;
import java.util.UUID;

public record Matricula(UUID id, UUID alunoId, UUID turmaId, StatusMatricula status, BigDecimal valorMensalidade) {
    public static Matricula nova (UUID alunoId, UUID turmaId, BigDecimal valorMensalidade) {
        return new Matricula(UUID.randomUUID(), alunoId, turmaId, StatusMatricula.ATIVA, valorMensalidade);
    }

    public Matricula cancelar () {
        return new Matricula(id, alunoId, turmaId, StatusMatricula.CANCELADA, valorMensalidade);
    }
}
