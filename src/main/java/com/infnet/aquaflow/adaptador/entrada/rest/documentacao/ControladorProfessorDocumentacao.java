package com.infnet.aquaflow.adaptador.entrada.rest.documentacao;

import com.infnet.aquaflow.adaptador.tratamento.ErroPadraoResposta;
import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarProfessorRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.ProfessorResposta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "Professores", description = "Operacoes de cadastro e manutencao de professores")
public interface ControladorProfessorDocumentacao {

    @Operation(summary = "Criar professor")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Professor criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    ProfessorResposta criar (@Valid CriarProfessorRequisicao requisicao);

    @Operation(summary = "Buscar professor por id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Professor encontrado"),
            @ApiResponse(responseCode = "404", description = "Professor nao encontrado", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    ProfessorResposta buscar (UUID professorId);

    @Operation(summary = "Atualizar professor")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Professor atualizado"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "404", description = "Professor nao encontrado", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    ProfessorResposta atualizar (UUID professorId, @Valid CriarProfessorRequisicao requisicao);

    @Operation(summary = "Excluir professor")
    @ApiResponses({@ApiResponse(responseCode = "204", description = "Professor excluido"),
            @ApiResponse(responseCode = "404", description = "Professor nao encontrado", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErroPadraoResposta.class)))})
    ResponseEntity<Void> excluir (UUID professorId);
}

