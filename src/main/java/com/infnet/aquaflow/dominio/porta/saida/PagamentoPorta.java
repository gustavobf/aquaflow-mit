package com.infnet.aquaflow.dominio.porta.saida;

import java.math.BigDecimal;

public interface PagamentoPorta {
    void cobrar (BigDecimal valor, String tipoPagamento);
}
