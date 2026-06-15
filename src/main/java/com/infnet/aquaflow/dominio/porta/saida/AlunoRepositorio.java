package com.infnet.aquaflow.dominio.porta.saida;

import com.infnet.aquaflow.dominio.entidade.Aluno;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AlunoRepositorio {
    Aluno salvar (Aluno aluno);

    Optional<Aluno> buscarPorId (UUID alunoId);

    List<Aluno> listarTodos ();

    void excluirPorId (UUID alunoId);
}
