package com.infnet.aquaflow.aplicacao.caso_de_uso.relatorio;

import com.infnet.aquaflow.aplicacao.dto.resposta.*;
import com.infnet.aquaflow.dominio.enumeracao.*;
import com.infnet.aquaflow.dominio.porta.saida.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Service
public class GerarRelatorioPagamentoCasoDeUso {
    private final TurmaRepositorio turmaRepositorio;
    private final MatriculaRepositorio matriculaRepositorio;

    public GerarRelatorioPagamentoCasoDeUso (TurmaRepositorio turmaRepositorio,
                                             MatriculaRepositorio matriculaRepositorio) {
        this.turmaRepositorio = turmaRepositorio;
        this.matriculaRepositorio = matriculaRepositorio;
    }

    public RelatorioPagamentoResumoResposta gerarResumo () {
        int matriculasAtivas = 0;
        int matriculasCanceladas = 0;
        BigDecimal valorMensalidadeAtiva = BigDecimal.ZERO;
        BigDecimal valorMensalidadeCancelada = BigDecimal.ZERO;

        for (var turma : turmaRepositorio.listarTodos()) {
            var matriculas = matriculaRepositorio.listarPorTurma(turma.id());

            for (var matricula : matriculas) {
                if (matricula.status() == StatusMatricula.ATIVA) {
                    matriculasAtivas++;
                    valorMensalidadeAtiva = valorMensalidadeAtiva.add(matricula.valorMensalidade());
                } else {
                    matriculasCanceladas++;
                    valorMensalidadeCancelada = valorMensalidadeCancelada.add(matricula.valorMensalidade());
                }
            }
        }

        BigDecimal valorTotal = valorMensalidadeAtiva.add(valorMensalidadeCancelada);

        return new RelatorioPagamentoResumoResposta(matriculasAtivas, matriculasCanceladas, valorMensalidadeAtiva,
                valorMensalidadeCancelada, valorTotal);
    }

    public List<RelatorioPagamentoTurmaResposta> gerarPorTurma () {
        return turmaRepositorio.listarTodos().stream().map(turma -> {
            var matriculas = matriculaRepositorio.listarPorTurma(turma.id());
            int quantidadeAtivas = 0;
            int quantidadeCanceladas = 0;
            BigDecimal receitaMensalAtiva = BigDecimal.ZERO;

            for (var matricula : matriculas) {
                if (matricula.status() == StatusMatricula.ATIVA) {
                    quantidadeAtivas++;
                    receitaMensalAtiva = receitaMensalAtiva.add(matricula.valorMensalidade());
                } else {
                    quantidadeCanceladas++;
                }
            }

            return new RelatorioPagamentoTurmaResposta(turma.id(), turma.nome(), quantidadeAtivas, quantidadeCanceladas,
                    receitaMensalAtiva);
        }).sorted(Comparator.comparing(RelatorioPagamentoTurmaResposta::nomeTurma)).toList();
    }
}

