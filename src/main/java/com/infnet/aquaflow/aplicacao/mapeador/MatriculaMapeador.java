package com.infnet.aquaflow.aplicacao.mapeador;

import com.infnet.aquaflow.aplicacao.dto.resposta.MatriculaResposta;
import com.infnet.aquaflow.dominio.entidade.Matricula;

public final class MatriculaMapeador {
    private MatriculaMapeador () {
    }

    public static MatriculaResposta paraResposta (Matricula matricula) {
        return new MatriculaResposta(matricula.id(), matricula.alunoId(), matricula.turmaId(), matricula.status(),
                matricula.valorMensalidade());
    }
}
