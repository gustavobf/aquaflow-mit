package com.infnet.aquaflow.infraestrutura.persistencia.adaptador;

import com.infnet.aquaflow.dominio.entidade.Professor;
import com.infnet.aquaflow.dominio.porta.saida.ProfessorRepositorio;
import com.infnet.aquaflow.infraestrutura.persistencia.entidade.ProfessorJpa;
import com.infnet.aquaflow.infraestrutura.persistencia.repositorio.JpaProfessorRepositorio;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProfessorRepositorioAdaptador implements ProfessorRepositorio {
    private final JpaProfessorRepositorio jpaProfessorRepositorio;

    public ProfessorRepositorioAdaptador (JpaProfessorRepositorio jpaProfessorRepositorio) {
        this.jpaProfessorRepositorio = jpaProfessorRepositorio;
    }

    @Override
    public Professor salvar (Professor professor) {
        return paraDominio(jpaProfessorRepositorio.save(paraJpa(professor)));
    }

    @Override
    public Optional<Professor> buscarPorId (UUID professorId) {
        return jpaProfessorRepositorio.findById(professorId).map(this::paraDominio);
    }

    @Override
    public List<Professor> listarTodos () {
        return jpaProfessorRepositorio.findAll().stream().map(this::paraDominio).toList();
    }

    @Override
    public void excluirPorId (UUID professorId) {
        jpaProfessorRepositorio.deleteById(professorId);
    }

    private ProfessorJpa paraJpa (Professor professor) {
        return new ProfessorJpa(professor.id(), professor.nome(), professor.especialidade());
    }

    private Professor paraDominio (ProfessorJpa professorJpa) {
        return new Professor(professorJpa.getId(), professorJpa.getNome(), professorJpa.getEspecialidade());
    }
}
