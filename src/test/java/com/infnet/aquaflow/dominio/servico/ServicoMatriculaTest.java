package com.infnet.aquaflow.dominio.servico;

import com.infnet.aquaflow.aplicacao.dto.requisicao.MatriculaRequisicao;
import com.infnet.aquaflow.dominio.entidade.Aluno;
import com.infnet.aquaflow.dominio.entidade.Matricula;
import com.infnet.aquaflow.dominio.entidade.Turma;
import com.infnet.aquaflow.dominio.enumeracao.NivelTurma;
import com.infnet.aquaflow.dominio.enumeracao.StatusAluno;
import com.infnet.aquaflow.dominio.enumeracao.StatusMatricula;
import com.infnet.aquaflow.dominio.estrategia.pagamento.FabricaEstrategiaPagamento;
import com.infnet.aquaflow.dominio.excecao.RegraDeNegocioException;
import com.infnet.aquaflow.dominio.porta.saida.AlunoRepositorio;
import com.infnet.aquaflow.dominio.porta.saida.MatriculaRepositorio;
import com.infnet.aquaflow.dominio.porta.saida.PagamentoPorta;
import com.infnet.aquaflow.dominio.porta.saida.TurmaRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServicoMatriculaTest {

    @Mock
    private AlunoRepositorio alunoRepositorio;
    @Mock
    private TurmaRepositorio turmaRepositorio;
    @Mock
    private MatriculaRepositorio matriculaRepositorio;
    @Mock
    private PagamentoPorta pagamentoPorta;

    @InjectMocks
    private ServicoMatricula servicoMatricula;

    @BeforeEach
    void setUp () {
        servicoMatricula = new ServicoMatricula(alunoRepositorio, turmaRepositorio, matriculaRepositorio,
                pagamentoPorta, new FabricaEstrategiaPagamento());
    }

    @Test
    void deveMatricularQuandoAlunoETurmaExistem () {
        UUID alunoId = UUID.randomUUID();
        UUID turmaId = UUID.randomUUID();
        MatriculaRequisicao requisicao = new MatriculaRequisicao(alunoId, turmaId, new BigDecimal("199.90"), "PIX");

        when(alunoRepositorio.buscarPorId(alunoId)).thenReturn(
                Optional.of(new Aluno(alunoId, "Ana", "ana@x.com", StatusAluno.ATIVO)));
        when(turmaRepositorio.buscarPorId(turmaId)).thenReturn(
                Optional.of(new Turma(turmaId, "Turma A", NivelTurma.BASICO, UUID.randomUUID(), 10)));
        when(matriculaRepositorio.salvar(any(Matricula.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Matricula matricula = servicoMatricula.matricular(requisicao);

        assertEquals(alunoId, matricula.alunoId());
        assertEquals(turmaId, matricula.turmaId());
        assertEquals(StatusMatricula.ATIVA, matricula.status());

        verify(pagamentoPorta).cobrar(new BigDecimal("199.90"), "PIX");
        verify(matriculaRepositorio).salvar(any(Matricula.class));
    }

    @Test
    void deveLancarExcecaoQuandoAlunoNaoExiste () {
        MatriculaRequisicao requisicao = new MatriculaRequisicao(UUID.randomUUID(), UUID.randomUUID(),
                new BigDecimal("200"), "PIX");
        when(alunoRepositorio.buscarPorId(requisicao.alunoId())).thenReturn(Optional.empty());

        assertThrows(RegraDeNegocioException.class, () -> servicoMatricula.matricular(requisicao));
    }

    @Test
    void deveLancarExcecaoQuandoTurmaNaoExiste () {
        UUID alunoId = UUID.randomUUID();
        UUID turmaId = UUID.randomUUID();
        MatriculaRequisicao requisicao = new MatriculaRequisicao(alunoId, turmaId, new BigDecimal("200"), "PIX");

        when(alunoRepositorio.buscarPorId(alunoId)).thenReturn(
                Optional.of(new Aluno(alunoId, "Ana", "ana@x.com", StatusAluno.ATIVO)));
        when(turmaRepositorio.buscarPorId(turmaId)).thenReturn(Optional.empty());

        assertThrows(RegraDeNegocioException.class, () -> servicoMatricula.matricular(requisicao));
    }

    @Test
    void deveCancelarMatricula () {
        Matricula matricula = new Matricula(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
                StatusMatricula.ATIVA, new BigDecimal("250"));
        when(matriculaRepositorio.salvar(any(Matricula.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Matricula cancelada = servicoMatricula.cancelar(matricula);

        ArgumentCaptor<Matricula> captor = ArgumentCaptor.forClass(Matricula.class);
        verify(matriculaRepositorio).salvar(captor.capture());
        assertEquals(StatusMatricula.CANCELADA, captor.getValue().status());
        assertEquals(StatusMatricula.CANCELADA, cancelada.status());
    }
}

