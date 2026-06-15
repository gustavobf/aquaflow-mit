package com.infnet.aquaflow.dominio.estrategia.pagamento;

public class PagamentoPix implements EstrategiaPagamento {
    @Override
    public String tipo () {
        return "PIX";
    }
}
