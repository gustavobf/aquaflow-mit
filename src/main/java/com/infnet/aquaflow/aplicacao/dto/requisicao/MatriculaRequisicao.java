package com.infnet.aquaflow.aplicacao.dto.requisicao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.util.UUID;

public record MatriculaRequisicao(@NotNull @Schema(example = "8d09a76a-0ff1-41f8-a088-2449a129c7da") UUID alunoId,

                                  @NotNull @Schema(example = "00a7358d-a58e-4f2f-b7f8-a6d95e856eff") UUID turmaId,

                                  @NotNull @DecimalMin(value = "0.01") @Schema(example = "199.90") BigDecimal valorMensalidade,

                                  @NotBlank @Pattern(regexp = "(?i)PIX|CARTAO|DINHEIRO") @Schema(example = "PIX") String tipoPagamento) {
}
