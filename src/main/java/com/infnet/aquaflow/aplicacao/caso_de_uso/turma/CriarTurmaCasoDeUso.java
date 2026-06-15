package com.infnet.aquaflow.aplicacao.caso_de_uso.turma;

import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarTurmaRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.TurmaResposta;
import com.infnet.aquaflow.aplicacao.mapeador.TurmaMapeador;
import com.infnet.aquaflow.dominio.entidade.Turma;
import com.infnet.aquaflow.dominio.porta.entrada.CriarTurmaPorta;
import com.infnet.aquaflow.dominio.porta.saida.TurmaRepositorio;
import org.springframework.stereotype.Service;

@Service
public class CriarTurmaCasoDeUso implements CriarTurmaPorta {
    private final TurmaRepositorio turmaRepositorio;

    public CriarTurmaCasoDeUso (TurmaRepositorio turmaRepositorio) {
        this.turmaRepositorio = turmaRepositorio;
    }

    @Override
    public TurmaResposta executar (CriarTurmaRequisicao requisicao) {
        Turma turma = Turma.nova(requisicao.nome(), requisicao.nivel(), requisicao.professorId(),
                requisicao.capacidadeMaxima());
        return TurmaMapeador.paraResposta(turmaRepositorio.salvar(turma));
    }
}
