package com.infnet.aquaflow.aplicacao.caso_de_uso.aluno;

import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarAlunoRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.AlunoResposta;
import com.infnet.aquaflow.aplicacao.mapeador.AlunoMapeador;
import com.infnet.aquaflow.dominio.entidade.Aluno;
import com.infnet.aquaflow.dominio.excecao.AlunoNaoEncontradoException;
import com.infnet.aquaflow.dominio.porta.saida.AlunoRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtualizarAlunoCasoDeUso {
    private final AlunoRepositorio alunoRepositorio;

    public AtualizarAlunoCasoDeUso (AlunoRepositorio alunoRepositorio) {
        this.alunoRepositorio = alunoRepositorio;
    }

    public AlunoResposta executar (UUID alunoId, CriarAlunoRequisicao requisicao) {
        Aluno existente = alunoRepositorio.buscarPorId(alunoId)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno nao encontrado."));
        Aluno atualizado = existente.atualizar(requisicao.nome(), requisicao.email());
        return AlunoMapeador.paraResposta(alunoRepositorio.salvar(atualizado));
    }
}
