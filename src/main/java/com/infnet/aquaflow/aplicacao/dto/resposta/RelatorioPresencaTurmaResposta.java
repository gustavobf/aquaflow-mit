package com.infnet.aquaflow.aplicacao.dto.resposta;

import java.math.*;
import java.util.*;

public record RelatorioPresencaTurmaResposta(UUID turmaId, String nomeTurma, int totalRegistros, int totalPresencas,
                                             int totalAusencias, BigDecimal taxaPresencaPercentual) {
}

