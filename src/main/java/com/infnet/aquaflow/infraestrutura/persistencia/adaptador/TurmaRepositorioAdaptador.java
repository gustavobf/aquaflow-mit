package com.infnet.aquaflow.infraestrutura.persistencia.adaptador;

import com.infnet.aquaflow.dominio.entidade.Turma;
import com.infnet.aquaflow.dominio.porta.saida.TurmaRepositorio;
import com.infnet.aquaflow.infraestrutura.persistencia.entidade.TurmaJpa;
import com.infnet.aquaflow.infraestrutura.persistencia.repositorio.JpaTurmaRepositorio;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TurmaRepositorioAdaptador implements TurmaRepositorio {
    private final JpaTurmaRepositorio jpaTurmaRepositorio;

    public TurmaRepositorioAdaptador (JpaTurmaRepositorio jpaTurmaRepositorio) {
        this.jpaTurmaRepositorio = jpaTurmaRepositorio;
    }

    @Override
    public Turma salvar (Turma turma) {
        return paraDominio(jpaTurmaRepositorio.save(paraJpa(turma)));
    }

    @Override
    public Optional<Turma> buscarPorId (UUID turmaId) {
        return jpaTurmaRepositorio.findById(turmaId).map(this::paraDominio);
    }

    @Override
    public List<Turma> listarTodos () {
        return jpaTurmaRepositorio.findAll().stream().map(this::paraDominio).toList();
    }

    private TurmaJpa paraJpa (Turma turma) {
        return new TurmaJpa(turma.id(), turma.nome(), turma.nivel(), turma.professorId(), turma.capacidadeMaxima());
    }

    private Turma paraDominio (TurmaJpa turmaJpa) {
        return new Turma(turmaJpa.getId(), turmaJpa.getNome(), turmaJpa.getNivel(), turmaJpa.getProfessorId(),
                turmaJpa.getCapacidadeMaxima());
    }
}
