package com.infnet.aquaflow.adaptador.entrada.rest;

import com.infnet.aquaflow.adaptador.entrada.rest.documentacao.ControladorProfessorDocumentacao;
import com.infnet.aquaflow.aplicacao.caso_de_uso.professor.AtualizarProfessorCasoDeUso;
import com.infnet.aquaflow.aplicacao.caso_de_uso.professor.BuscarProfessorCasoDeUso;
import com.infnet.aquaflow.aplicacao.caso_de_uso.professor.CriarProfessorCasoDeUso;
import com.infnet.aquaflow.aplicacao.caso_de_uso.professor.ExcluirProfessorCasoDeUso;
import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarProfessorRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.ProfessorResposta;
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
@RequestMapping("/api/professores")
public class ControladorProfessor implements ControladorProfessorDocumentacao {
    private final CriarProfessorCasoDeUso criarProfessorCasoDeUso;
    private final BuscarProfessorCasoDeUso buscarProfessorCasoDeUso;
    private final AtualizarProfessorCasoDeUso atualizarProfessorCasoDeUso;
    private final ExcluirProfessorCasoDeUso excluirProfessorCasoDeUso;

    public ControladorProfessor (CriarProfessorCasoDeUso criarProfessorCasoDeUso,
                                 BuscarProfessorCasoDeUso buscarProfessorCasoDeUso,
                                 AtualizarProfessorCasoDeUso atualizarProfessorCasoDeUso,
                                 ExcluirProfessorCasoDeUso excluirProfessorCasoDeUso) {
        this.criarProfessorCasoDeUso = criarProfessorCasoDeUso;
        this.buscarProfessorCasoDeUso = buscarProfessorCasoDeUso;
        this.atualizarProfessorCasoDeUso = atualizarProfessorCasoDeUso;
        this.excluirProfessorCasoDeUso = excluirProfessorCasoDeUso;
    }

    @PostMapping
    @Override
    public ProfessorResposta criar (@Valid @RequestBody CriarProfessorRequisicao requisicao) {
        return criarProfessorCasoDeUso.executar(requisicao);
    }

    @GetMapping("/{professorId}")
    @Override
    public ProfessorResposta buscar (@PathVariable UUID professorId) {
        return buscarProfessorCasoDeUso.executar(professorId);
    }

    @PutMapping("/{professorId}")
    @Override
    public ProfessorResposta atualizar (@PathVariable UUID professorId,
                                        @Valid @RequestBody CriarProfessorRequisicao requisicao) {
        return atualizarProfessorCasoDeUso.executar(professorId, requisicao);
    }

    @DeleteMapping("/{professorId}")
    @Override
    public ResponseEntity<Void> excluir (@PathVariable UUID professorId) {
        excluirProfessorCasoDeUso.executar(professorId);
        return ResponseEntity.noContent().build();
    }
}
