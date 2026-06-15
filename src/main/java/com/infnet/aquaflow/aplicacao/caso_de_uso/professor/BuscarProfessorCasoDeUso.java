package com.infnet.aquaflow.aplicacao.caso_de_uso.professor;

import com.infnet.aquaflow.aplicacao.dto.resposta.ProfessorResposta;
import com.infnet.aquaflow.aplicacao.mapeador.ProfessorMapeador;
import com.infnet.aquaflow.dominio.excecao.ProfessorNaoEncontradoException;
import com.infnet.aquaflow.dominio.porta.saida.ProfessorRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscarProfessorCasoDeUso {
    private final ProfessorRepositorio professorRepositorio;

    public BuscarProfessorCasoDeUso (ProfessorRepositorio professorRepositorio) {
        this.professorRepositorio = professorRepositorio;
    }

    public ProfessorResposta executar (UUID professorId) {
        return professorRepositorio.buscarPorId(professorId).map(ProfessorMapeador::paraResposta)
                .orElseThrow(() -> new ProfessorNaoEncontradoException("Professor nao encontrado."));
    }
}
