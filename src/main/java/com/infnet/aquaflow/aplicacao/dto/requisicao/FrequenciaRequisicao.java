package com.infnet.aquaflow.aplicacao.dto.requisicao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.UUID;

public record FrequenciaRequisicao(@NotNull @Schema(example = "62c92590-7e2f-4823-b4cb-6f5c8ac7f66d") UUID matriculaId,

                                   @NotNull @Schema(example = "2026-06-13") LocalDate dataAula,

                                   @NotBlank @Pattern(regexp = "(?i)PRESENTE|AUSENTE") @Schema(example = "PRESENTE") String tipoFrequencia) {
}
