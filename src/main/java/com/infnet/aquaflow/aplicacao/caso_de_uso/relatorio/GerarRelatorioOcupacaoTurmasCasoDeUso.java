package com.infnet.aquaflow.aplicacao.caso_de_uso.relatorio;

import com.infnet.aquaflow.aplicacao.dto.resposta.*;
import com.infnet.aquaflow.dominio.enumeracao.*;
import com.infnet.aquaflow.dominio.porta.saida.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Service
public class GerarRelatorioOcupacaoTurmasCasoDeUso {
    private final TurmaRepositorio turmaRepositorio;
    private final MatriculaRepositorio matriculaRepositorio;

    public GerarRelatorioOcupacaoTurmasCasoDeUso (TurmaRepositorio turmaRepositorio,
                                                  MatriculaRepositorio matriculaRepositorio) {
        this.turmaRepositorio = turmaRepositorio;
        this.matriculaRepositorio = matriculaRepositorio;
    }

    public List<RelatorioTurmaOcupacaoResposta> executar () {
        return turmaRepositorio.listarTodos().stream().map(turma -> {
            int matriculasAtivas = (int) matriculaRepositorio.listarPorTurma(turma.id()).stream()
                    .filter(matricula -> matricula.status() == StatusMatricula.ATIVA).count();

            int vagasDisponiveis = Math.max(turma.capacidadeMaxima() - matriculasAtivas, 0);
            BigDecimal taxaOcupacao = calcularPercentual(matriculasAtivas, turma.capacidadeMaxima());

            return new RelatorioTurmaOcupacaoResposta(turma.id(), turma.nome(), turma.nivel().name(),
                    turma.capacidadeMaxima(), matriculasAtivas, vagasDisponiveis, taxaOcupacao);
        }).sorted(Comparator.comparing(RelatorioTurmaOcupacaoResposta::nomeTurma)).toList();
    }

    private BigDecimal calcularPercentual (int numerador, int denominador) {
        if (denominador == 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        return BigDecimal.valueOf(numerador).multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(denominador), 2, RoundingMode.HALF_UP);
    }
}

