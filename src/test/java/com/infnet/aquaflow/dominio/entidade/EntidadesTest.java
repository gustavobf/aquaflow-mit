package com.infnet.aquaflow.dominio.entidade;

import com.infnet.aquaflow.dominio.enumeracao.NivelTurma;
import com.infnet.aquaflow.dominio.enumeracao.StatusAluno;
import com.infnet.aquaflow.dominio.enumeracao.StatusMatricula;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EntidadesTest {

    @Test
    void alunoDeveIniciarAtivoEPermitirAtualizacaoEInativacao () {
        Aluno aluno = Aluno.novo("Ana", "ana@x.com");
        assertNotNull(aluno.id());
        assertEquals(StatusAluno.ATIVO, aluno.status());

        Aluno atualizado = aluno.atualizar("Ana Silva", "ana.silva@x.com");
        assertEquals("Ana Silva", atualizado.nome());
        assertEquals("ana.silva@x.com", atualizado.email());

        Aluno inativo = atualizado.inativar();
        assertEquals(StatusAluno.INATIVO, inativo.status());
    }

    @Test
    void professorETurmaDevemAtualizarDados () {
        Professor professor = Professor.novo("Carlos", "Crawl");
        Professor professorAtualizado = professor.atualizar("Carlos Souza", "Borboleta");
        assertEquals("Carlos Souza", professorAtualizado.nome());
        assertEquals("Borboleta", professorAtualizado.especialidade());

        Turma turma = Turma.nova("Infantil", NivelTurma.BASICO, UUID.randomUUID(), 10);
        Turma turmaAtualizada = turma.atualizar("Infantil A", NivelTurma.INTERMEDIARIO, UUID.randomUUID(), 15);
        assertEquals("Infantil A", turmaAtualizada.nome());
        assertEquals(NivelTurma.INTERMEDIARIO, turmaAtualizada.nivel());
        assertEquals(15, turmaAtualizada.capacidadeMaxima());
    }

    @Test
    void matriculaEFrequenciaDevemManterEstadoEsperado () {
        Matricula matricula = Matricula.nova(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal("199.90"));
        assertEquals(StatusMatricula.ATIVA, matricula.status());

        Matricula cancelada = matricula.cancelar();
        assertEquals(StatusMatricula.CANCELADA, cancelada.status());

        Frequencia frequencia = Frequencia.nova(matricula.id(), LocalDate.now(), true);
        assertNotNull(frequencia.id());
        assertEquals(matricula.id(), frequencia.matriculaId());
    }
}

