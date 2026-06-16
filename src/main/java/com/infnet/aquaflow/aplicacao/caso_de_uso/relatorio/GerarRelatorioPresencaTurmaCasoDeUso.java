package com.infnet.aquaflow.aplicacao.caso_de_uso.relatorio;

import com.infnet.aquaflow.aplicacao.dto.resposta.*;
import com.infnet.aquaflow.dominio.entidade.*;
import com.infnet.aquaflow.dominio.excecao.*;
import com.infnet.aquaflow.dominio.porta.saida.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Service
public class GerarRelatorioPresencaTurmaCasoDeUso {
    private final TurmaRepositorio turmaRepositorio;
    private final MatriculaRepositorio matriculaRepositorio;
    private final FrequenciaRepositorio frequenciaRepositorio;

    public GerarRelatorioPresencaTurmaCasoDeUso (TurmaRepositorio turmaRepositorio,
                                                 MatriculaRepositorio matriculaRepositorio,
                                                 FrequenciaRepositorio frequenciaRepositorio) {
        this.turmaRepositorio = turmaRepositorio;
        this.matriculaRepositorio = matriculaRepositorio;
        this.frequenciaRepositorio = frequenciaRepositorio;
    }

    public RelatorioPresencaTurmaResposta executar (UUID turmaId) {
        var turma = turmaRepositorio.buscarPorId(turmaId)
                .orElseThrow(() -> new TurmaNaoEncontradaException("Turma nao encontrada."));

        List<Matricula> matriculas = matriculaRepositorio.listarPorTurma(turmaId);

        int totalPresencas = 0;
        int totalAusencias = 0;

        for (Matricula matricula : matriculas) {
            var frequencias = frequenciaRepositorio.listarPorMatricula(matricula.id());
            totalPresencas += (int) frequencias.stream().filter(frequencia -> frequencia.presente()).count();
            totalAusencias += (int) frequencias.stream().filter(frequencia -> !frequencia.presente()).count();
        }

        int totalRegistros = totalPresencas + totalAusencias;
        BigDecimal taxaPresenca = calcularPercentual(totalPresencas, totalRegistros);

        return new RelatorioPresencaTurmaResposta(turma.id(), turma.nome(), totalRegistros, totalPresencas,
                totalAusencias, taxaPresenca);
    }

    private BigDecimal calcularPercentual (int numerador, int denominador) {
        if (denominador == 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        return BigDecimal.valueOf(numerador).multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(denominador), 2, RoundingMode.HALF_UP);
    }
}

