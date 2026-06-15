package com.infnet.aquaflow.aplicacao.caso_de_uso.frequencia;

import com.infnet.aquaflow.aplicacao.dto.resposta.FrequenciaResposta;
import com.infnet.aquaflow.dominio.entidade.Frequencia;
import com.infnet.aquaflow.dominio.porta.saida.FrequenciaRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarFrequenciaCasoDeUsoTest {

    @Mock
    private FrequenciaRepositorio frequenciaRepositorio;

    @InjectMocks
    private BuscarFrequenciaCasoDeUso casoDeUso;

    @Test
    void deveRetornarListaMapeadaDeFrequencias () {
        UUID matriculaId = UUID.randomUUID();
        Frequencia f1 = new Frequencia(UUID.randomUUID(), matriculaId, LocalDate.of(2026, 6, 10), true);
        Frequencia f2 = new Frequencia(UUID.randomUUID(), matriculaId, LocalDate.of(2026, 6, 11), false);

        when(frequenciaRepositorio.listarPorMatricula(matriculaId)).thenReturn(List.of(f1, f2));

        List<FrequenciaResposta> respostas = casoDeUso.executar(matriculaId);

        assertEquals(2, respostas.size());
        assertEquals(f1.id(), respostas.get(0).id());
        assertEquals(false, respostas.get(1).presente());
    }
}

