package com.infnet.aquaflow.dominio.porta.entrada;

import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarAlunoRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.AlunoResposta;

public interface CriarAlunoPorta {
    AlunoResposta executar (CriarAlunoRequisicao requisicao);
}
