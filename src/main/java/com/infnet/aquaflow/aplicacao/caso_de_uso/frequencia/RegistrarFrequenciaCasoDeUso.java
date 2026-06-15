package com.infnet.aquaflow.aplicacao.caso_de_uso.frequencia;

import com.infnet.aquaflow.aplicacao.dto.requisicao.FrequenciaRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.FrequenciaResposta;
import com.infnet.aquaflow.dominio.entidade.Frequencia;
import com.infnet.aquaflow.dominio.porta.entrada.RegistrarFrequenciaPorta;
import com.infnet.aquaflow.dominio.servico.ServicoFrequencia;
import org.springframework.stereotype.Service;

@Service
public class RegistrarFrequenciaCasoDeUso implements RegistrarFrequenciaPorta {
    private final ServicoFrequencia servicoFrequencia;

    public RegistrarFrequenciaCasoDeUso (ServicoFrequencia servicoFrequencia) {
        this.servicoFrequencia = servicoFrequencia;
    }

    @Override
    public FrequenciaResposta executar (FrequenciaRequisicao requisicao) {
        Frequencia frequencia = servicoFrequencia.registrar(requisicao);
        return new FrequenciaResposta(frequencia.id(), frequencia.matriculaId(), frequencia.dataAula(),
                frequencia.presente());
    }
}
