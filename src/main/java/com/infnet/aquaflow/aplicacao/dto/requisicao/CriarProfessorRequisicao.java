package com.infnet.aquaflow.aplicacao.dto.requisicao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CriarProfessorRequisicao(@NotBlank @Schema(example = "Carlos Souza") String nome,

                                       @NotBlank @Schema(example = "Nado crawl") String especialidade) {
}
