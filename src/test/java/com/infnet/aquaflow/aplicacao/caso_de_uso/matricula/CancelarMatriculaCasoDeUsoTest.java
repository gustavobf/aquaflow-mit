package com.infnet.aquaflow.aplicacao.caso_de_uso.matricula;

import com.infnet.aquaflow.aplicacao.dto.resposta.MatriculaResposta;
import com.infnet.aquaflow.dominio.entidade.Matricula;
import com.infnet.aquaflow.dominio.enumeracao.StatusMatricula;
import com.infnet.aquaflow.dominio.excecao.RegraDeNegocioException;
import com.infnet.aquaflow.dominio.porta.saida.MatriculaRepositorio;
import com.infnet.aquaflow.dominio.servico.ServicoMatricula;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CancelarMatriculaCasoDeUsoTest {

    @Mock
    private MatriculaRepositorio matriculaRepositorio;
    @Mock
    private ServicoMatricula servicoMatricula;

    @InjectMocks
    private CancelarMatriculaCasoDeUso casoDeUso;

    @Test
    void deveCancelarMatriculaQuandoExiste () {
        UUID id = UUID.randomUUID();
        Matricula ativa = new Matricula(id, UUID.randomUUID(), UUID.randomUUID(), StatusMatricula.ATIVA,
                new BigDecimal("100"));
        Matricula cancelada = ativa.cancelar();

        when(matriculaRepositorio.buscarPorId(id)).thenReturn(Optional.of(ativa));
        when(servicoMatricula.cancelar(ativa)).thenReturn(cancelada);

        MatriculaResposta resposta = casoDeUso.executar(id);

        assertEquals(StatusMatricula.CANCELADA, resposta.status());
    }

    @Test
    void deveLancarExcecaoQuandoMatriculaNaoExiste () {
        UUID id = UUID.randomUUID();
        when(matriculaRepositorio.buscarPorId(id)).thenReturn(Optional.empty());

        assertThrows(RegraDeNegocioException.class, () -> casoDeUso.executar(id));
    }
}

