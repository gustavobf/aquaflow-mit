package com.infnet.aquaflow.dominio.porta.saida;

import com.infnet.aquaflow.dominio.entidade.Turma;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TurmaRepositorio {
    Turma salvar (Turma turma);

    Optional<Turma> buscarPorId (UUID turmaId);

    List<Turma> listarTodos ();
}
