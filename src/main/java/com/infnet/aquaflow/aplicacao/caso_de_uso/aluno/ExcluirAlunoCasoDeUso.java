package com.infnet.aquaflow.aplicacao.caso_de_uso.aluno;

import com.infnet.aquaflow.dominio.porta.saida.AlunoRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExcluirAlunoCasoDeUso {
    private final AlunoRepositorio alunoRepositorio;

    public ExcluirAlunoCasoDeUso (AlunoRepositorio alunoRepositorio) {
        this.alunoRepositorio = alunoRepositorio;
    }

    public void executar (UUID alunoId) {
        alunoRepositorio.excluirPorId(alunoId);
    }
}
