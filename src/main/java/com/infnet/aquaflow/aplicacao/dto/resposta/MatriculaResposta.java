package com.infnet.aquaflow.aplicacao.dto.resposta;

import com.infnet.aquaflow.dominio.enumeracao.StatusMatricula;

import java.math.BigDecimal;
import java.util.UUID;

public record MatriculaResposta(UUID id, UUID alunoId, UUID turmaId, StatusMatricula status,
                                BigDecimal valorMensalidade) {
}
