package com.infnet.aquaflow.aplicacao.dto.requisicao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CriarAlunoRequisicao(@NotBlank @Schema(example = "Ana Silva") String nome,

                                   @NotBlank @Email @Schema(example = "ana@email.com") String email) {
}
