package com.infnet.aquaflow.dominio.porta.entrada;

import com.infnet.aquaflow.aplicacao.dto.requisicao.MatriculaRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.MatriculaResposta;

public interface MatricularAlunoPorta {
    MatriculaResposta executar (MatriculaRequisicao requisicao);
}
