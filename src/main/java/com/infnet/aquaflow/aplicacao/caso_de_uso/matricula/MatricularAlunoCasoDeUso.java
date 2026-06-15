package com.infnet.aquaflow.aplicacao.caso_de_uso.matricula;

import com.infnet.aquaflow.aplicacao.dto.requisicao.MatriculaRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.MatriculaResposta;
import com.infnet.aquaflow.aplicacao.mapeador.MatriculaMapeador;
import com.infnet.aquaflow.dominio.entidade.Matricula;
import com.infnet.aquaflow.dominio.porta.entrada.MatricularAlunoPorta;
import com.infnet.aquaflow.dominio.servico.ServicoMatricula;
import org.springframework.stereotype.Service;

@Service
public class MatricularAlunoCasoDeUso implements MatricularAlunoPorta {
    private final ServicoMatricula servicoMatricula;

    public MatricularAlunoCasoDeUso (ServicoMatricula servicoMatricula) {
        this.servicoMatricula = servicoMatricula;
    }

    @Override
    public MatriculaResposta executar (MatriculaRequisicao requisicao) {
        Matricula matricula = servicoMatricula.matricular(requisicao);
        return MatriculaMapeador.paraResposta(matricula);
    }
}
