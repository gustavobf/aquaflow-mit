package com.infnet.aquaflow.aplicacao.mapeador;

import com.infnet.aquaflow.aplicacao.dto.resposta.ProfessorResposta;
import com.infnet.aquaflow.dominio.entidade.Professor;

public final class ProfessorMapeador {
    private ProfessorMapeador () {
    }

    public static ProfessorResposta paraResposta (Professor professor) {
        return new ProfessorResposta(professor.id(), professor.nome(), professor.especialidade());
    }
}
