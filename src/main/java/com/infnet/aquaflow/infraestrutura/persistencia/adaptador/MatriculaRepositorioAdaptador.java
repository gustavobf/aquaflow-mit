package com.infnet.aquaflow.infraestrutura.persistencia.adaptador;

import com.infnet.aquaflow.dominio.entidade.Matricula;
import com.infnet.aquaflow.dominio.porta.saida.MatriculaRepositorio;
import com.infnet.aquaflow.infraestrutura.persistencia.entidade.MatriculaJpa;
import com.infnet.aquaflow.infraestrutura.persistencia.repositorio.JpaMatriculaRepositorio;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MatriculaRepositorioAdaptador implements MatriculaRepositorio {
    private final JpaMatriculaRepositorio jpaMatriculaRepositorio;

    public MatriculaRepositorioAdaptador (JpaMatriculaRepositorio jpaMatriculaRepositorio) {
        this.jpaMatriculaRepositorio = jpaMatriculaRepositorio;
    }

    @Override
    public Matricula salvar (Matricula matricula) {
        return paraDominio(jpaMatriculaRepositorio.save(paraJpa(matricula)));
    }

    @Override
    public Optional<Matricula> buscarPorId (UUID matriculaId) {
        return jpaMatriculaRepositorio.findById(matriculaId).map(this::paraDominio);
    }

    @Override
    public List<Matricula> listarPorAluno (UUID alunoId) {
        return jpaMatriculaRepositorio.findByAlunoId(alunoId).stream().map(this::paraDominio).toList();
    }

    @Override
    public List<Matricula> listarPorTurma (UUID turmaId) {
        return jpaMatriculaRepositorio.findByTurmaId(turmaId).stream().map(this::paraDominio).toList();
    }

    private MatriculaJpa paraJpa (Matricula matricula) {
        return new MatriculaJpa(matricula.id(), matricula.alunoId(), matricula.turmaId(), matricula.status(),
                matricula.valorMensalidade());
    }

    private Matricula paraDominio (MatriculaJpa matriculaJpa) {
        return new Matricula(matriculaJpa.getId(), matriculaJpa.getAlunoId(), matriculaJpa.getTurmaId(),
                matriculaJpa.getStatus(), matriculaJpa.getValorMensalidade());
    }
}
