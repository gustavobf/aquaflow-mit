package com.infnet.aquaflow.infraestrutura.persistencia.adaptador;

import com.infnet.aquaflow.dominio.entidade.Aluno;
import com.infnet.aquaflow.dominio.porta.saida.AlunoRepositorio;
import com.infnet.aquaflow.infraestrutura.persistencia.entidade.AlunoJpa;
import com.infnet.aquaflow.infraestrutura.persistencia.repositorio.JpaAlunoRepositorio;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AlunoRepositorioAdaptador implements AlunoRepositorio {
    private final JpaAlunoRepositorio jpaAlunoRepositorio;

    public AlunoRepositorioAdaptador (JpaAlunoRepositorio jpaAlunoRepositorio) {
        this.jpaAlunoRepositorio = jpaAlunoRepositorio;
    }

    @Override
    public Aluno salvar (Aluno aluno) {
        return paraDominio(jpaAlunoRepositorio.save(paraJpa(aluno)));
    }

    @Override
    public Optional<Aluno> buscarPorId (UUID alunoId) {
        return jpaAlunoRepositorio.findById(alunoId).map(this::paraDominio);
    }

    @Override
    public List<Aluno> listarTodos () {
        return jpaAlunoRepositorio.findAll().stream().map(this::paraDominio).toList();
    }

    @Override
    public void excluirPorId (UUID alunoId) {
        jpaAlunoRepositorio.deleteById(alunoId);
    }

    private AlunoJpa paraJpa (Aluno aluno) {
        return new AlunoJpa(aluno.id(), aluno.nome(), aluno.email(), aluno.status());
    }

    private Aluno paraDominio (AlunoJpa alunoJpa) {
        return new Aluno(alunoJpa.getId(), alunoJpa.getNome(), alunoJpa.getEmail(), alunoJpa.getStatus());
    }
}
