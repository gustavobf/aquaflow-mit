package com.infnet.aquaflow.dominio.servico;

import com.infnet.aquaflow.aplicacao.dto.requisicao.FrequenciaRequisicao;
import com.infnet.aquaflow.dominio.entidade.Frequencia;
import com.infnet.aquaflow.dominio.estrategia.frequencia.FabricaEstrategiaFrequencia;
import com.infnet.aquaflow.dominio.excecao.RegraDeNegocioException;
import com.infnet.aquaflow.dominio.porta.saida.FrequenciaRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServicoFrequenciaTest {

    @Mock
    private FrequenciaRepositorio frequenciaRepositorio;

    @InjectMocks
    private ServicoFrequencia servicoFrequencia;

    @BeforeEach
    void setUp () {
        servicoFrequencia = new ServicoFrequencia(frequenciaRepositorio, new FabricaEstrategiaFrequencia());
    }

    @Test
    void deveRegistrarFrequenciaPresente () {
        FrequenciaRequisicao requisicao = new FrequenciaRequisicao(UUID.randomUUID(), LocalDate.now(), "PRESENTE");
        when(frequenciaRepositorio.salvar(any(Frequencia.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Frequencia frequencia = servicoFrequencia.registrar(requisicao);

        assertTrue(frequencia.presente());
    }

    @Test
    void deveRegistrarFrequenciaAusente () {
        FrequenciaRequisicao requisicao = new FrequenciaRequisicao(UUID.randomUUID(), LocalDate.now(), "AUSENTE");
        when(frequenciaRepositorio.salvar(any(Frequencia.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Frequencia frequencia = servicoFrequencia.registrar(requisicao);

        assertFalse(frequencia.presente());
    }

    @Test
    void deveLancarExcecaoQuandoTipoInvalido () {
        FrequenciaRequisicao requisicao = new FrequenciaRequisicao(UUID.randomUUID(), LocalDate.now(), "TALVEZ");
        assertThrows(RegraDeNegocioException.class, () -> servicoFrequencia.registrar(requisicao));
    }
}

