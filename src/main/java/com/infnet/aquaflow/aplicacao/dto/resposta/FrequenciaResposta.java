package com.infnet.aquaflow.aplicacao.dto.resposta;

import java.time.LocalDate;
import java.util.UUID;

public record FrequenciaResposta(UUID id, UUID matriculaId, LocalDate dataAula, boolean presente) {
}
