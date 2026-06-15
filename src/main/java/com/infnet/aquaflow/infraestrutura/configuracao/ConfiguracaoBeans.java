package com.infnet.aquaflow.infraestrutura.configuracao;

import com.infnet.aquaflow.dominio.estrategia.frequencia.FabricaEstrategiaFrequencia;
import com.infnet.aquaflow.dominio.estrategia.pagamento.FabricaEstrategiaPagamento;

public class ConfiguracaoBeans {
    public FabricaEstrategiaPagamento fabricaEstrategiaPagamento () {
        return new FabricaEstrategiaPagamento();
    }

    public FabricaEstrategiaFrequencia fabricaEstrategiaFrequencia () {
        return new FabricaEstrategiaFrequencia();
    }
}
