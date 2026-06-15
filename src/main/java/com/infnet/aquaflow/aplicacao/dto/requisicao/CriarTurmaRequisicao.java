package com.infnet.aquaflow.aplicacao.dto.requisicao;

import com.infnet.aquaflow.dominio.enumeracao.NivelTurma;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CriarTurmaRequisicao(@NotBlank @Schema(example = "Turma Infantil 1") String nome,

                                   @NotNull @Schema(example = "BASICO") NivelTurma nivel,

                                   @NotNull @Schema(example = "f22ce06d-b95d-4dd6-b08b-72d4ced9429a") UUID professorId,

                                   @Min(1) @Schema(example = "20") int capacidadeMaxima) {
}
