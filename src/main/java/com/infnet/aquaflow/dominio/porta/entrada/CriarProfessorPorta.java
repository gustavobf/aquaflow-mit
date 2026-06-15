package com.infnet.aquaflow.dominio.porta.entrada;

import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarProfessorRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.ProfessorResposta;

public interface CriarProfessorPorta {
    ProfessorResposta executar (CriarProfessorRequisicao requisicao);
}
