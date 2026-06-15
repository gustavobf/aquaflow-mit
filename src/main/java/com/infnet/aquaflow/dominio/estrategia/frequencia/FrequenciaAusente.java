package com.infnet.aquaflow.dominio.estrategia.frequencia;

public class FrequenciaAusente implements EstrategiaFrequencia {
    @Override
    public boolean presente () {
        return false;
    }
}
