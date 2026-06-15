package com.infnet.aquaflow.adaptador.entrada.rest;

import com.infnet.aquaflow.adaptador.entrada.rest.documentacao.ControladorMatriculaDocumentacao;
import com.infnet.aquaflow.aplicacao.caso_de_uso.matricula.CancelarMatriculaCasoDeUso;
import com.infnet.aquaflow.aplicacao.caso_de_uso.matricula.MatricularAlunoCasoDeUso;
import com.infnet.aquaflow.aplicacao.dto.requisicao.MatriculaRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.MatriculaResposta;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/matriculas")
public class ControladorMatricula implements ControladorMatriculaDocumentacao {
    private final MatricularAlunoCasoDeUso matricularAlunoCasoDeUso;
    private final CancelarMatriculaCasoDeUso cancelarMatriculaCasoDeUso;

    public ControladorMatricula (MatricularAlunoCasoDeUso matricularAlunoCasoDeUso,
                                 CancelarMatriculaCasoDeUso cancelarMatriculaCasoDeUso) {
        this.matricularAlunoCasoDeUso = matricularAlunoCasoDeUso;
        this.cancelarMatriculaCasoDeUso = cancelarMatriculaCasoDeUso;
    }

    @PostMapping
    @Override
    public MatriculaResposta matricular (@Valid @RequestBody MatriculaRequisicao requisicao) {
        return matricularAlunoCasoDeUso.executar(requisicao);
    }

    @PatchMapping("/{matriculaId}/cancelar")
    @Override
    public MatriculaResposta cancelar (@PathVariable UUID matriculaId) {
        return cancelarMatriculaCasoDeUso.executar(matriculaId);
    }
}
