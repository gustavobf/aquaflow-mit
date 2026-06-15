package com.infnet.aquaflow.dominio.estrategia.frequencia;

import com.infnet.aquaflow.dominio.excecao.RegraDeNegocioException;
import org.springframework.stereotype.Component;

@Component
public class FabricaEstrategiaFrequencia {
    public EstrategiaFrequencia criar (String tipoFrequencia) {
        return switch (tipoFrequencia.toUpperCase()) {
            case "PRESENTE" -> new FrequenciaPresente();
            case "AUSENTE" -> new FrequenciaAusente();
            default -> throw new RegraDeNegocioException("Tipo de frequencia invalido: " + tipoFrequencia);
        };
    }
}
