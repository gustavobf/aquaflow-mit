package com.infnet.aquaflow.adaptador.entrada.rest;

import com.infnet.aquaflow.adaptador.entrada.rest.documentacao.ControladorTurmaDocumentacao;
import com.infnet.aquaflow.aplicacao.caso_de_uso.turma.AtualizarTurmaCasoDeUso;
import com.infnet.aquaflow.aplicacao.caso_de_uso.turma.BuscarTurmaCasoDeUso;
import com.infnet.aquaflow.aplicacao.caso_de_uso.turma.CriarTurmaCasoDeUso;
import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarTurmaRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.TurmaResposta;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/turmas")
public class ControladorTurma implements ControladorTurmaDocumentacao {
    private final CriarTurmaCasoDeUso criarTurmaCasoDeUso;
    private final BuscarTurmaCasoDeUso buscarTurmaCasoDeUso;
    private final AtualizarTurmaCasoDeUso atualizarTurmaCasoDeUso;

    public ControladorTurma (CriarTurmaCasoDeUso criarTurmaCasoDeUso, BuscarTurmaCasoDeUso buscarTurmaCasoDeUso,
                             AtualizarTurmaCasoDeUso atualizarTurmaCasoDeUso) {
        this.criarTurmaCasoDeUso = criarTurmaCasoDeUso;
        this.buscarTurmaCasoDeUso = buscarTurmaCasoDeUso;
        this.atualizarTurmaCasoDeUso = atualizarTurmaCasoDeUso;
    }

    @PostMapping
    @Override
    public TurmaResposta criar (@Valid @RequestBody CriarTurmaRequisicao requisicao) {
        return criarTurmaCasoDeUso.executar(requisicao);
    }

    @GetMapping("/{turmaId}")
    @Override
    public TurmaResposta buscar (@PathVariable UUID turmaId) {
        return buscarTurmaCasoDeUso.executar(turmaId);
    }

    @PutMapping("/{turmaId}")
    @Override
    public TurmaResposta atualizar (@PathVariable UUID turmaId, @Valid @RequestBody CriarTurmaRequisicao requisicao) {
        return atualizarTurmaCasoDeUso.executar(turmaId, requisicao);
    }
}
