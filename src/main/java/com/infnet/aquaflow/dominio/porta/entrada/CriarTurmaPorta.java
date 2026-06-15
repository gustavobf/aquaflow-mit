package com.infnet.aquaflow.dominio.porta.entrada;

import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarTurmaRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.TurmaResposta;

public interface CriarTurmaPorta {
    TurmaResposta executar (CriarTurmaRequisicao requisicao);
}
