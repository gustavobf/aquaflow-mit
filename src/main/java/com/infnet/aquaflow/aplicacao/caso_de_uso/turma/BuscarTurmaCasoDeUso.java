package com.infnet.aquaflow.aplicacao.caso_de_uso.turma;

import com.infnet.aquaflow.aplicacao.dto.resposta.TurmaResposta;
import com.infnet.aquaflow.aplicacao.mapeador.TurmaMapeador;
import com.infnet.aquaflow.dominio.excecao.TurmaNaoEncontradaException;
import com.infnet.aquaflow.dominio.porta.saida.TurmaRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscarTurmaCasoDeUso {
    private final TurmaRepositorio turmaRepositorio;

    public BuscarTurmaCasoDeUso (TurmaRepositorio turmaRepositorio) {
        this.turmaRepositorio = turmaRepositorio;
    }

    public TurmaResposta executar (UUID turmaId) {
        return turmaRepositorio.buscarPorId(turmaId).map(TurmaMapeador::paraResposta)
                .orElseThrow(() -> new TurmaNaoEncontradaException("Turma nao encontrada."));
    }
}
