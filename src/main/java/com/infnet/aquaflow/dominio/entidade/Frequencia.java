package com.infnet.aquaflow.dominio.entidade;

import java.time.LocalDate;
import java.util.UUID;

public record Frequencia(UUID id, UUID matriculaId, LocalDate dataAula, boolean presente) {
    public static Frequencia nova (UUID matriculaId, LocalDate dataAula, boolean presente) {
        return new Frequencia(UUID.randomUUID(), matriculaId, dataAula, presente);
    }
}
