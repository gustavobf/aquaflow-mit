package com.infnet.aquaflow.dominio.porta.saida;

import com.infnet.aquaflow.dominio.entidade.Professor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfessorRepositorio {
    Professor salvar (Professor professor);

    Optional<Professor> buscarPorId (UUID professorId);

    List<Professor> listarTodos ();

    void excluirPorId (UUID professorId);
}
