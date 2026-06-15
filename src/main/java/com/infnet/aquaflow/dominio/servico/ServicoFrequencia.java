package com.infnet.aquaflow.dominio.servico;

import com.infnet.aquaflow.aplicacao.dto.requisicao.FrequenciaRequisicao;
import com.infnet.aquaflow.dominio.entidade.Frequencia;
import com.infnet.aquaflow.dominio.estrategia.frequencia.EstrategiaFrequencia;
import com.infnet.aquaflow.dominio.estrategia.frequencia.FabricaEstrategiaFrequencia;
import com.infnet.aquaflow.dominio.porta.saida.FrequenciaRepositorio;
import org.springframework.stereotype.Service;

@Service
public class ServicoFrequencia {
    private final FrequenciaRepositorio frequenciaRepositorio;
    private final FabricaEstrategiaFrequencia fabricaEstrategiaFrequencia;

    public ServicoFrequencia (FrequenciaRepositorio frequenciaRepositorio,
                              FabricaEstrategiaFrequencia fabricaEstrategiaFrequencia) {
        this.frequenciaRepositorio = frequenciaRepositorio;
        this.fabricaEstrategiaFrequencia = fabricaEstrategiaFrequencia;
    }

    public Frequencia registrar (FrequenciaRequisicao requisicao) {
        EstrategiaFrequencia estrategia = fabricaEstrategiaFrequencia.criar(requisicao.tipoFrequencia());
        Frequencia frequencia = Frequencia.nova(requisicao.matriculaId(), requisicao.dataAula(), estrategia.presente());
        return frequenciaRepositorio.salvar(frequencia);
    }
}
