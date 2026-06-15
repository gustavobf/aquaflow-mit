package com.infnet.aquaflow.adaptador.entrada.rest.documentacao;

import com.infnet.aquaflow.adaptador.tratamento.ErroPadraoResposta;
import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarAlunoRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.AlunoResposta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "Alunos", description = "Operacoes de cadastro e manutencao de alunos")
public interface ControladorAlunoDocumentacao {

    @Operation(summary = "Criar aluno")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Aluno criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    AlunoResposta criar (@Valid CriarAlunoRequisicao requisicao);

    @Operation(summary = "Buscar aluno por id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Aluno encontrado"),
            @ApiResponse(responseCode = "404", description = "Aluno nao encontrado", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    AlunoResposta buscar (UUID alunoId);

    @Operation(summary = "Atualizar aluno")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Aluno atualizado"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "404", description = "Aluno nao encontrado", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    AlunoResposta atualizar (UUID alunoId, @Valid CriarAlunoRequisicao requisicao);

    @Operation(summary = "Excluir aluno")
    @ApiResponses({@ApiResponse(responseCode = "204", description = "Aluno excluido"),
            @ApiResponse(responseCode = "404", description = "Aluno nao encontrado", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    ResponseEntity<Void> excluir (UUID alunoId);
}

