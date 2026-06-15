package com.infnet.aquaflow.dominio.porta.saida;

import com.infnet.aquaflow.dominio.entidade.Frequencia;

import java.util.List;
import java.util.UUID;

public interface FrequenciaRepositorio {
    Frequencia salvar (Frequencia frequencia);

    List<Frequencia> listarPorMatricula (UUID matriculaId);
}
