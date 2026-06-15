package com.infnet.aquaflow.aplicacao.caso_de_uso.professor;

import com.infnet.aquaflow.dominio.porta.saida.ProfessorRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExcluirProfessorCasoDeUso {
    private final ProfessorRepositorio professorRepositorio;

    public ExcluirProfessorCasoDeUso (ProfessorRepositorio professorRepositorio) {
        this.professorRepositorio = professorRepositorio;
    }

    public void executar (UUID professorId) {
        professorRepositorio.excluirPorId(professorId);
    }
}
