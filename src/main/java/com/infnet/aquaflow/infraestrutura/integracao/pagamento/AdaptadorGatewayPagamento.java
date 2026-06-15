package com.infnet.aquaflow.infraestrutura.integracao.pagamento;

import com.infnet.aquaflow.dominio.porta.saida.PagamentoPorta;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AdaptadorGatewayPagamento implements PagamentoPorta {
    private final ClienteGatewayPagamento clienteGatewayPagamento;

    public AdaptadorGatewayPagamento (ClienteGatewayPagamento clienteGatewayPagamento) {
        this.clienteGatewayPagamento = clienteGatewayPagamento;
    }

    @Override
    public void cobrar (BigDecimal valor, String tipoPagamento) {
        clienteGatewayPagamento.cobrar(valor, tipoPagamento);
    }
}
