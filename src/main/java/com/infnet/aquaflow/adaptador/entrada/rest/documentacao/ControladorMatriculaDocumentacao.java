package com.infnet.aquaflow.adaptador.entrada.rest.documentacao;

import com.infnet.aquaflow.adaptador.tratamento.ErroPadraoResposta;
import com.infnet.aquaflow.aplicacao.dto.requisicao.MatriculaRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.MatriculaResposta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.UUID;

@Tag(name = "Matriculas", description = "Operacoes de matricula e cancelamento")
public interface ControladorMatriculaDocumentacao {

    @Operation(summary = "Matricular aluno")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Matricula realizada"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    MatriculaResposta matricular (@Valid MatriculaRequisicao requisicao);

    @Operation(summary = "Cancelar matricula")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Matricula cancelada"),
            @ApiResponse(responseCode = "404", description = "Matricula nao encontrada", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    MatriculaResposta cancelar (UUID matriculaId);
}

