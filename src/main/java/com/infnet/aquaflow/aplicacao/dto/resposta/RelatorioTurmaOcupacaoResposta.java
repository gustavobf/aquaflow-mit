package com.infnet.aquaflow.aplicacao.dto.resposta;

import java.math.*;
import java.util.*;

public record RelatorioTurmaOcupacaoResposta(UUID turmaId, String nomeTurma, String nivelTurma, int capacidadeMaxima,
                                             int matriculasAtivas, int vagasDisponiveis,
                                             BigDecimal taxaOcupacaoPercentual) {
}

