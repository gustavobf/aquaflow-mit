package com.infnet.aquaflow.adaptador.entrada.rest.documentacao;

import com.infnet.aquaflow.adaptador.tratamento.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.http.*;

import java.util.*;

@Tag(name = "Relatorios", description = "Operacoes de relatorios gerenciais")
public interface ControladorRelatorioDocumentacao {

    @Operation(summary = "Gerar relatorio de presencas por turma")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Arquivo PDF gerado", content = @Content(mediaType = "application/pdf")),
            @ApiResponse(responseCode = "404", description = "Turma nao encontrada", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    ResponseEntity<byte[]> presencasPorTurma (UUID turmaId);

    @Operation(summary = "Gerar relatorio de ocupacao das turmas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Arquivo PDF gerado", content = @Content(mediaType = "application/pdf")),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    ResponseEntity<byte[]> ocupacaoTurmas ();

    @Operation(summary = "Gerar resumo de pagamentos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Arquivo PDF gerado", content = @Content(mediaType = "application/pdf")),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    ResponseEntity<byte[]> resumoPagamentos ();

    @Operation(summary = "Gerar relatorio de pagamentos por turma")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Arquivo PDF gerado", content = @Content(mediaType = "application/pdf")),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    ResponseEntity<byte[]> pagamentosPorTurma ();
}

