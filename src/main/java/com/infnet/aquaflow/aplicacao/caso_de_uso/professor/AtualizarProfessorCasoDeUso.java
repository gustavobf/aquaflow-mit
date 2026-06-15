package com.infnet.aquaflow.aplicacao.caso_de_uso.professor;

import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarProfessorRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.ProfessorResposta;
import com.infnet.aquaflow.aplicacao.mapeador.ProfessorMapeador;
import com.infnet.aquaflow.dominio.entidade.Professor;
import com.infnet.aquaflow.dominio.excecao.ProfessorNaoEncontradoException;
import com.infnet.aquaflow.dominio.porta.saida.ProfessorRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtualizarProfessorCasoDeUso {
    private final ProfessorRepositorio professorRepositorio;

    public AtualizarProfessorCasoDeUso (ProfessorRepositorio professorRepositorio) {
        this.professorRepositorio = professorRepositorio;
    }

    public ProfessorResposta executar (UUID professorId, CriarProfessorRequisicao requisicao) {
        Professor existente = professorRepositorio.buscarPorId(professorId)
                .orElseThrow(() -> new ProfessorNaoEncontradoException("Professor nao encontrado."));
        Professor atualizado = existente.atualizar(requisicao.nome(), requisicao.especialidade());
        return ProfessorMapeador.paraResposta(professorRepositorio.salvar(atualizado));
    }
}
