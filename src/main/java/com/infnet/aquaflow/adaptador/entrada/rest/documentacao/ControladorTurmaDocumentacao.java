package com.infnet.aquaflow.adaptador.entrada.rest.documentacao;

import com.infnet.aquaflow.adaptador.tratamento.ErroPadraoResposta;
import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarTurmaRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.TurmaResposta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.UUID;

@Tag(name = "Turmas", description = "Operacoes de cadastro e manutencao de turmas")
public interface ControladorTurmaDocumentacao {

    @Operation(summary = "Criar turma")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Turma criada"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    TurmaResposta criar (@Valid CriarTurmaRequisicao requisicao);

    @Operation(summary = "Buscar turma por id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Turma encontrada"),
            @ApiResponse(responseCode = "404", description = "Turma nao encontrada", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    TurmaResposta buscar (UUID turmaId);

    @Operation(summary = "Atualizar turma")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Turma atualizada"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "404", description = "Turma nao encontrada", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    TurmaResposta atualizar (UUID turmaId, @Valid CriarTurmaRequisicao requisicao);
}

