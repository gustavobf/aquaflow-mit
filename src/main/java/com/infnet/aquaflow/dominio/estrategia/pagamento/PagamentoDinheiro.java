package com.infnet.aquaflow.dominio.estrategia.pagamento;

public class PagamentoDinheiro implements EstrategiaPagamento {
    @Override
    public String tipo () {
        return "DINHEIRO";
    }
}
