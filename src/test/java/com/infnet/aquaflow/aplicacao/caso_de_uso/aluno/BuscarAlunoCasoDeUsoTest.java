package com.infnet.aquaflow.aplicacao.caso_de_uso.aluno;

import com.infnet.aquaflow.aplicacao.dto.resposta.AlunoResposta;
import com.infnet.aquaflow.dominio.entidade.Aluno;
import com.infnet.aquaflow.dominio.enumeracao.StatusAluno;
import com.infnet.aquaflow.dominio.excecao.AlunoNaoEncontradoException;
import com.infnet.aquaflow.dominio.porta.saida.AlunoRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarAlunoCasoDeUsoTest {

    @Mock
    private AlunoRepositorio alunoRepositorio;

    @InjectMocks
    private BuscarAlunoCasoDeUso casoDeUso;

    @Test
    void deveBuscarAlunoPorId () {
        UUID id = UUID.randomUUID();
        when(alunoRepositorio.buscarPorId(id)).thenReturn(
                Optional.of(new Aluno(id, "Ana", "ana@x.com", StatusAluno.ATIVO)));

        AlunoResposta resposta = casoDeUso.executar(id);

        assertEquals(id, resposta.id());
        assertEquals("Ana", resposta.nome());
    }

    @Test
    void deveLancarExcecaoQuandoAlunoNaoExiste () {
        UUID id = UUID.randomUUID();
        when(alunoRepositorio.buscarPorId(id)).thenReturn(Optional.empty());

        assertThrows(AlunoNaoEncontradoException.class, () -> casoDeUso.executar(id));
    }
}

