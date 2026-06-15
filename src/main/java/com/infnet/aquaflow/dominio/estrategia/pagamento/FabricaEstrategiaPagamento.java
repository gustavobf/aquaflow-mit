package com.infnet.aquaflow.dominio.estrategia.pagamento;

import com.infnet.aquaflow.dominio.excecao.RegraDeNegocioException;
import org.springframework.stereotype.Component;

@Component
public class FabricaEstrategiaPagamento {
    public EstrategiaPagamento criar (String tipoPagamento) {
        return switch (tipoPagamento.toUpperCase()) {
            case "PIX" -> new PagamentoPix();
            case "CARTAO" -> new PagamentoCartao();
            case "DINHEIRO" -> new PagamentoDinheiro();
            default -> throw new RegraDeNegocioException("Tipo de pagamento invalido: " + tipoPagamento);
        };
    }
}
