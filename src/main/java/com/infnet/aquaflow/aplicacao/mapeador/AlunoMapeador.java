package com.infnet.aquaflow.aplicacao.mapeador;

import com.infnet.aquaflow.aplicacao.dto.resposta.AlunoResposta;
import com.infnet.aquaflow.dominio.entidade.Aluno;

public final class AlunoMapeador {
    private AlunoMapeador () {
    }

    public static AlunoResposta paraResposta (Aluno aluno) {
        return new AlunoResposta(aluno.id(), aluno.nome(), aluno.email(), aluno.status());
    }
}
