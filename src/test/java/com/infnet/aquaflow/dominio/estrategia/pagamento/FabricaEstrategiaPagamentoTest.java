package com.infnet.aquaflow.dominio.estrategia.pagamento;

import com.infnet.aquaflow.dominio.excecao.RegraDeNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FabricaEstrategiaPagamentoTest {

    private final FabricaEstrategiaPagamento fabrica = new FabricaEstrategiaPagamento();

    @Test
    void deveCriarPagamentoPix () {
        assertInstanceOf(PagamentoPix.class, fabrica.criar("PIX"));
    }

    @Test
    void deveCriarPagamentoCartao () {
        assertInstanceOf(PagamentoCartao.class, fabrica.criar("cartao"));
    }

    @Test
    void deveCriarPagamentoDinheiro () {
        assertInstanceOf(PagamentoDinheiro.class, fabrica.criar("DINHEIRO"));
    }

    @Test
    void deveLancarExcecaoQuandoTipoInvalido () {
        assertThrows(RegraDeNegocioException.class, () -> fabrica.criar("BOLETO"));
    }
}

