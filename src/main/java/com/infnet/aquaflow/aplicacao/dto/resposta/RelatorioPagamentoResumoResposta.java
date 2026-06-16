package com.infnet.aquaflow.aplicacao.dto.resposta;

import java.math.*;

public record RelatorioPagamentoResumoResposta(int quantidadeMatriculasAtivas, int quantidadeMatriculasCanceladas,
                                               BigDecimal valorMensalidadeAtiva, BigDecimal valorMensalidadeCancelada,
                                               BigDecimal valorMensalidadeTotal) {
}

