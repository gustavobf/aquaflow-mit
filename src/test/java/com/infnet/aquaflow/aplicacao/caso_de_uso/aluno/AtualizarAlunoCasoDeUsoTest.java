package com.infnet.aquaflow.aplicacao.caso_de_uso.aluno;

import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarAlunoRequisicao;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizarAlunoCasoDeUsoTest {

    @Mock
    private AlunoRepositorio alunoRepositorio;

    @InjectMocks
    private AtualizarAlunoCasoDeUso casoDeUso;

    @Test
    void deveAtualizarAluno () {
        UUID id = UUID.randomUUID();
        Aluno existente = new Aluno(id, "Ana", "ana@x.com", StatusAluno.ATIVO);

        when(alunoRepositorio.buscarPorId(id)).thenReturn(Optional.of(existente));
        when(alunoRepositorio.salvar(any(Aluno.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AlunoResposta resposta = casoDeUso.executar(id, new CriarAlunoRequisicao("Ana Silva", "ana.silva@x.com"));

        assertEquals("Ana Silva", resposta.nome());
        assertEquals("ana.silva@x.com", resposta.email());
    }

    @Test
    void deveLancarExcecaoQuandoAlunoNaoExiste () {
        UUID id = UUID.randomUUID();
        when(alunoRepositorio.buscarPorId(id)).thenReturn(Optional.empty());

        assertThrows(AlunoNaoEncontradoException.class,
                () -> casoDeUso.executar(id, new CriarAlunoRequisicao("Ana", "ana@x.com")));
    }
}

