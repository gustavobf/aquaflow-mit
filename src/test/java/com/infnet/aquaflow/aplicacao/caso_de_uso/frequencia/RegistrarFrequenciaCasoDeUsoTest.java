package com.infnet.aquaflow.aplicacao.caso_de_uso.frequencia;

import com.infnet.aquaflow.aplicacao.dto.requisicao.FrequenciaRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.FrequenciaResposta;
import com.infnet.aquaflow.dominio.entidade.Frequencia;
import com.infnet.aquaflow.dominio.servico.ServicoFrequencia;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrarFrequenciaCasoDeUsoTest {

    @Mock
    private ServicoFrequencia servicoFrequencia;

    @InjectMocks
    private RegistrarFrequenciaCasoDeUso casoDeUso;

    @Test
    void deveRegistrarFrequencia () {
        UUID matriculaId = UUID.randomUUID();
        FrequenciaRequisicao requisicao = new FrequenciaRequisicao(matriculaId, LocalDate.now(), "PRESENTE");
        Frequencia frequencia = new Frequencia(UUID.randomUUID(), matriculaId, requisicao.dataAula(), true);
        when(servicoFrequencia.registrar(requisicao)).thenReturn(frequencia);

        FrequenciaResposta resposta = casoDeUso.executar(requisicao);

        assertEquals(frequencia.id(), resposta.id());
        assertEquals(matriculaId, resposta.matriculaId());
        assertEquals(true, resposta.presente());
    }
}

