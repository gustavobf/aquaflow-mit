package com.infnet.aquaflow.dominio.estrategia.frequencia;

import com.infnet.aquaflow.dominio.excecao.RegraDeNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FabricaEstrategiaFrequenciaTest {

    private final FabricaEstrategiaFrequencia fabrica = new FabricaEstrategiaFrequencia();

    @Test
    void deveCriarFrequenciaPresente () {
        assertInstanceOf(FrequenciaPresente.class, fabrica.criar("PRESENTE"));
    }

    @Test
    void deveCriarFrequenciaAusente () {
        assertInstanceOf(FrequenciaAusente.class, fabrica.criar("ausente"));
    }

    @Test
    void deveLancarExcecaoQuandoTipoInvalido () {
        assertThrows(RegraDeNegocioException.class, () -> fabrica.criar("MEIA"));
    }
}

