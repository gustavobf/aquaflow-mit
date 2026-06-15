package com.infnet.aquaflow.aplicacao.caso_de_uso.frequencia;

import com.infnet.aquaflow.aplicacao.dto.resposta.FrequenciaResposta;
import com.infnet.aquaflow.dominio.entidade.Frequencia;
import com.infnet.aquaflow.dominio.porta.saida.FrequenciaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BuscarFrequenciaCasoDeUso {
    private final FrequenciaRepositorio frequenciaRepositorio;

    public BuscarFrequenciaCasoDeUso (FrequenciaRepositorio frequenciaRepositorio) {
        this.frequenciaRepositorio = frequenciaRepositorio;
    }

    public List<FrequenciaResposta> executar (UUID matriculaId) {
        return frequenciaRepositorio.listarPorMatricula(matriculaId).stream().map(this::paraResposta).toList();
    }

    private FrequenciaResposta paraResposta (Frequencia frequencia) {
        return new FrequenciaResposta(frequencia.id(), frequencia.matriculaId(), frequencia.dataAula(),
                frequencia.presente());
    }
}
