package com.infnet.aquaflow.aplicacao.mapeador;

import com.infnet.aquaflow.aplicacao.dto.resposta.TurmaResposta;
import com.infnet.aquaflow.dominio.entidade.Turma;

public final class TurmaMapeador {
    private TurmaMapeador () {
    }

    public static TurmaResposta paraResposta (Turma turma) {
        return new TurmaResposta(turma.id(), turma.nome(), turma.nivel(), turma.professorId(),
                turma.capacidadeMaxima());
    }
}
