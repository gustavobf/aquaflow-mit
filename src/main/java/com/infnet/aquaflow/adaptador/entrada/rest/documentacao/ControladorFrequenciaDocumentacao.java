package com.infnet.aquaflow.adaptador.entrada.rest.documentacao;

import com.infnet.aquaflow.adaptador.tratamento.ErroPadraoResposta;
import com.infnet.aquaflow.aplicacao.dto.requisicao.FrequenciaRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.FrequenciaResposta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@Tag(name = "Frequencias", description = "Operacoes de registro e consulta de frequencia")
public interface ControladorFrequenciaDocumentacao {

    @Operation(summary = "Registrar frequencia")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Frequencia registrada"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    FrequenciaResposta registrar (@Valid FrequenciaRequisicao requisicao);

    @Operation(summary = "Buscar frequencias por matricula")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Consulta realizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    List<FrequenciaResposta> buscarPorMatricula (UUID matriculaId);
}

