package com.infnet.aquaflow.aplicacao.caso_de_uso.aluno;

import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarAlunoRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.AlunoResposta;
import com.infnet.aquaflow.aplicacao.mapeador.AlunoMapeador;
import com.infnet.aquaflow.dominio.entidade.Aluno;
import com.infnet.aquaflow.dominio.porta.entrada.CriarAlunoPorta;
import com.infnet.aquaflow.dominio.porta.saida.AlunoRepositorio;
import org.springframework.stereotype.Service;

@Service
public class CriarAlunoCasoDeUso implements CriarAlunoPorta {
    private final AlunoRepositorio alunoRepositorio;

    public CriarAlunoCasoDeUso (AlunoRepositorio alunoRepositorio) {
        this.alunoRepositorio = alunoRepositorio;
    }

    @Override
    public AlunoResposta executar (CriarAlunoRequisicao requisicao) {
        Aluno aluno = Aluno.novo(requisicao.nome(), requisicao.email());
        return AlunoMapeador.paraResposta(alunoRepositorio.salvar(aluno));
    }
}
