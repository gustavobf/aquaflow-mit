package com.infnet.aquaflow.aplicacao.dto.resposta;

import java.math.*;
import java.util.*;

public record RelatorioPagamentoTurmaResposta(UUID turmaId, String nomeTurma, int quantidadeMatriculasAtivas,
                                              int quantidadeMatriculasCanceladas, BigDecimal receitaMensalAtiva) {
}

