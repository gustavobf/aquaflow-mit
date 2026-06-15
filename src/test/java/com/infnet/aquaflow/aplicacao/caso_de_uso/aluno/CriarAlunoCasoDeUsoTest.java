package com.infnet.aquaflow.aplicacao.caso_de_uso.aluno;

import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarAlunoRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.AlunoResposta;
import com.infnet.aquaflow.dominio.entidade.Aluno;
import com.infnet.aquaflow.dominio.enumeracao.StatusAluno;
import com.infnet.aquaflow.dominio.porta.saida.AlunoRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarAlunoCasoDeUsoTest {

    @Mock
    private AlunoRepositorio alunoRepositorio;

    @InjectMocks
    private CriarAlunoCasoDeUso casoDeUso;

    @Test
    void deveCriarAlunoERetornarResposta () {
        UUID id = UUID.randomUUID();
        when(alunoRepositorio.salvar(any(Aluno.class))).thenReturn(
                new Aluno(id, "Ana", "ana@x.com", StatusAluno.ATIVO));

        AlunoResposta resposta = casoDeUso.executar(new CriarAlunoRequisicao("Ana", "ana@x.com"));

        assertEquals(id, resposta.id());
        assertEquals("Ana", resposta.nome());
        assertEquals("ana@x.com", resposta.email());
        assertEquals(StatusAluno.ATIVO, resposta.status());
    }
}

