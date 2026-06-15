package com.infnet.aquaflow.dominio.porta.entrada;

import com.infnet.aquaflow.aplicacao.dto.requisicao.FrequenciaRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.FrequenciaResposta;

public interface RegistrarFrequenciaPorta {
    FrequenciaResposta executar (FrequenciaRequisicao requisicao);
}
