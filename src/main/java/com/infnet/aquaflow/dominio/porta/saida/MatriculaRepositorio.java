package com.infnet.aquaflow.dominio.porta.saida;

import com.infnet.aquaflow.dominio.entidade.Matricula;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MatriculaRepositorio {
    Matricula salvar (Matricula matricula);

    Optional<Matricula> buscarPorId (UUID matriculaId);

    List<Matricula> listarPorAluno (UUID alunoId);

    List<Matricula> listarPorTurma (UUID turmaId);
}
