package com.infnet.aquaflow.infraestrutura.integracao.pagamento;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ClienteGatewayPagamento {
    private final List<PagamentoFake> transacoes = new CopyOnWriteArrayList<>();

    public PagamentoFake cobrar (BigDecimal valor, String formaPagamento) {
        PagamentoFake pagamento = new PagamentoFake(UUID.randomUUID(), valor, formaPagamento, "APROVADO",
                Instant.now());
        transacoes.add(pagamento);
        return pagamento;
    }

    public List<PagamentoFake> listarTransacoes () {
        return List.copyOf(transacoes);
    }

    public record PagamentoFake(UUID id, BigDecimal valor, String formaPagamento, String status, Instant criadoEm) {
    }
}
