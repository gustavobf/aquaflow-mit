package com.infnet.aquaflow.aplicacao.caso_de_uso.matricula;

import com.infnet.aquaflow.aplicacao.dto.requisicao.MatriculaRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.MatriculaResposta;
import com.infnet.aquaflow.dominio.entidade.Matricula;
import com.infnet.aquaflow.dominio.enumeracao.StatusMatricula;
import com.infnet.aquaflow.dominio.servico.ServicoMatricula;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatricularAlunoCasoDeUsoTest {

    @Mock
    private ServicoMatricula servicoMatricula;

    @InjectMocks
    private MatricularAlunoCasoDeUso casoDeUso;

    @Test
    void deveMatricularEMapearResposta () {
        UUID matriculaId = UUID.randomUUID();
        UUID alunoId = UUID.randomUUID();
        UUID turmaId = UUID.randomUUID();
        MatriculaRequisicao requisicao = new MatriculaRequisicao(alunoId, turmaId, new BigDecimal("150"), "PIX");

        when(servicoMatricula.matricular(requisicao)).thenReturn(
                new Matricula(matriculaId, alunoId, turmaId, StatusMatricula.ATIVA, new BigDecimal("150")));

        MatriculaResposta resposta = casoDeUso.executar(requisicao);

        assertEquals(matriculaId, resposta.id());
        assertEquals(StatusMatricula.ATIVA, resposta.status());
    }
}

