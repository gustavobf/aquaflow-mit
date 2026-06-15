package com.infnet.aquaflow.adaptador.tratamento;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

@Schema(name = "ErroPadraoResposta", description = "Contrato padrao para respostas de erro da API")
public record ErroPadraoResposta(@Schema(example = "2026-06-13T13:05:00Z") Instant timestamp,

                                 @Schema(example = "400") int status,

                                 @Schema(example = "Bad Request") String erro,

                                 @Schema(example = "Dados invalidos na requisicao") String mensagem,

                                 @Schema(example = "/api/alunos") String caminho,

                                 List<DetalheErro> detalhes) {

    public record DetalheErro(@Schema(example = "email") String campo,

                              @Schema(example = "deve ser um endereco de e-mail valido") String mensagem) {
    }
}

