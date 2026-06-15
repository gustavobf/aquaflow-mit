package com.infnet.aquaflow.adaptador.entrada.rest;

import com.infnet.aquaflow.adaptador.entrada.rest.documentacao.ControladorAlunoDocumentacao;
import com.infnet.aquaflow.aplicacao.caso_de_uso.aluno.AtualizarAlunoCasoDeUso;
import com.infnet.aquaflow.aplicacao.caso_de_uso.aluno.BuscarAlunoCasoDeUso;
import com.infnet.aquaflow.aplicacao.caso_de_uso.aluno.CriarAlunoCasoDeUso;
import com.infnet.aquaflow.aplicacao.caso_de_uso.aluno.ExcluirAlunoCasoDeUso;
import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarAlunoRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.AlunoResposta;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/alunos")
public class ControladorAluno implements ControladorAlunoDocumentacao {
    private final CriarAlunoCasoDeUso criarAlunoCasoDeUso;
    private final BuscarAlunoCasoDeUso buscarAlunoCasoDeUso;
    private final AtualizarAlunoCasoDeUso atualizarAlunoCasoDeUso;
    private final ExcluirAlunoCasoDeUso excluirAlunoCasoDeUso;

    public ControladorAluno (CriarAlunoCasoDeUso criarAlunoCasoDeUso, BuscarAlunoCasoDeUso buscarAlunoCasoDeUso,
                             AtualizarAlunoCasoDeUso atualizarAlunoCasoDeUso,
                             ExcluirAlunoCasoDeUso excluirAlunoCasoDeUso) {
        this.criarAlunoCasoDeUso = criarAlunoCasoDeUso;
        this.buscarAlunoCasoDeUso = buscarAlunoCasoDeUso;
        this.atualizarAlunoCasoDeUso = atualizarAlunoCasoDeUso;
        this.excluirAlunoCasoDeUso = excluirAlunoCasoDeUso;
    }

    @PostMapping
    @Override
    public AlunoResposta criar (@Valid @RequestBody CriarAlunoRequisicao requisicao) {
        return criarAlunoCasoDeUso.executar(requisicao);
    }

    @GetMapping("/{alunoId}")
    @Override
    public AlunoResposta buscar (@PathVariable UUID alunoId) {
        return buscarAlunoCasoDeUso.executar(alunoId);
    }

    @PutMapping("/{alunoId}")
    @Override
    public AlunoResposta atualizar (@PathVariable UUID alunoId, @Valid @RequestBody CriarAlunoRequisicao requisicao) {
        return atualizarAlunoCasoDeUso.executar(alunoId, requisicao);
    }

    @DeleteMapping("/{alunoId}")
    @Override
    public ResponseEntity<Void> excluir (@PathVariable UUID alunoId) {
        excluirAlunoCasoDeUso.executar(alunoId);
        return ResponseEntity.noContent().build();
    }
}
