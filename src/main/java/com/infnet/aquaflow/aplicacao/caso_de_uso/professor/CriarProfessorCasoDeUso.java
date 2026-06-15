package com.infnet.aquaflow.aplicacao.caso_de_uso.professor;

import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarProfessorRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.ProfessorResposta;
import com.infnet.aquaflow.aplicacao.mapeador.ProfessorMapeador;
import com.infnet.aquaflow.dominio.entidade.Professor;
import com.infnet.aquaflow.dominio.porta.entrada.CriarProfessorPorta;
import com.infnet.aquaflow.dominio.porta.saida.ProfessorRepositorio;
import org.springframework.stereotype.Service;

@Service
public class CriarProfessorCasoDeUso implements CriarProfessorPorta {
    private final ProfessorRepositorio professorRepositorio;

    public CriarProfessorCasoDeUso (ProfessorRepositorio professorRepositorio) {
        this.professorRepositorio = professorRepositorio;
    }

    @Override
    public ProfessorResposta executar (CriarProfessorRequisicao requisicao) {
        Professor professor = Professor.novo(requisicao.nome(), requisicao.especialidade());
        return ProfessorMapeador.paraResposta(professorRepositorio.salvar(professor));
    }
}
