package com.infnet.aquaflow.aplicacao.caso_de_uso.aluno;

import com.infnet.aquaflow.aplicacao.dto.resposta.AlunoResposta;
import com.infnet.aquaflow.aplicacao.mapeador.AlunoMapeador;
import com.infnet.aquaflow.dominio.excecao.AlunoNaoEncontradoException;
import com.infnet.aquaflow.dominio.porta.saida.AlunoRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscarAlunoCasoDeUso {
    private final AlunoRepositorio alunoRepositorio;

    public BuscarAlunoCasoDeUso (AlunoRepositorio alunoRepositorio) {
        this.alunoRepositorio = alunoRepositorio;
    }

    public AlunoResposta executar (UUID alunoId) {
        return alunoRepositorio.buscarPorId(alunoId).map(AlunoMapeador::paraResposta)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno nao encontrado."));
    }
}
