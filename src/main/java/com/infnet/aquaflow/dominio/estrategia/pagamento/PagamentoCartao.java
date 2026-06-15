package com.infnet.aquaflow.dominio.estrategia.pagamento;

public class PagamentoCartao implements EstrategiaPagamento {
    @Override
    public String tipo () {
        return "CARTAO";
    }
}
