package com.infnet.aquaflow.infraestrutura.persistencia.adaptador;

import com.infnet.aquaflow.dominio.entidade.Frequencia;
import com.infnet.aquaflow.dominio.porta.saida.FrequenciaRepositorio;
import com.infnet.aquaflow.infraestrutura.persistencia.entidade.FrequenciaJpa;
import com.infnet.aquaflow.infraestrutura.persistencia.repositorio.JpaFrequenciaRepositorio;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class FrequenciaRepositorioAdaptador implements FrequenciaRepositorio {
    private final JpaFrequenciaRepositorio jpaFrequenciaRepositorio;

    public FrequenciaRepositorioAdaptador (JpaFrequenciaRepositorio jpaFrequenciaRepositorio) {
        this.jpaFrequenciaRepositorio = jpaFrequenciaRepositorio;
    }

    @Override
    public Frequencia salvar (Frequencia frequencia) {
        return paraDominio(jpaFrequenciaRepositorio.save(paraJpa(frequencia)));
    }

    @Override
    public List<Frequencia> listarPorMatricula (UUID matriculaId) {
        return jpaFrequenciaRepositorio.findByMatriculaIdOrderByDataAulaDesc(matriculaId).stream()
                .map(this::paraDominio).toList();
    }

    private FrequenciaJpa paraJpa (Frequencia frequencia) {
        return new FrequenciaJpa(frequencia.id(), frequencia.matriculaId(), frequencia.dataAula(),
                frequencia.presente());
    }

    private Frequencia paraDominio (FrequenciaJpa frequenciaJpa) {
        return new Frequencia(frequenciaJpa.getId(), frequenciaJpa.getMatriculaId(), frequenciaJpa.getDataAula(),
                frequenciaJpa.isPresente());
    }
}
