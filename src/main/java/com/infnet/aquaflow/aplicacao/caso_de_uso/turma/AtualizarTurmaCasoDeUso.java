package com.infnet.aquaflow.aplicacao.caso_de_uso.turma;

import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarTurmaRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.TurmaResposta;
import com.infnet.aquaflow.aplicacao.mapeador.TurmaMapeador;
import com.infnet.aquaflow.dominio.entidade.Turma;
import com.infnet.aquaflow.dominio.excecao.TurmaNaoEncontradaException;
import com.infnet.aquaflow.dominio.porta.saida.TurmaRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtualizarTurmaCasoDeUso {
    private final TurmaRepositorio turmaRepositorio;

    public AtualizarTurmaCasoDeUso (TurmaRepositorio turmaRepositorio) {
        this.turmaRepositorio = turmaRepositorio;
    }

    public TurmaResposta executar (UUID turmaId, CriarTurmaRequisicao requisicao) {
        Turma existente = turmaRepositorio.buscarPorId(turmaId)
                .orElseThrow(() -> new TurmaNaoEncontradaException("Turma nao encontrada."));
        Turma atualizada = existente.atualizar(requisicao.nome(), requisicao.nivel(), requisicao.professorId(),
                requisicao.capacidadeMaxima());
        return TurmaMapeador.paraResposta(turmaRepositorio.salvar(atualizada));
    }
}
