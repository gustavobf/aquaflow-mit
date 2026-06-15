package com.infnet.aquaflow.dominio.excecao;

public class TurmaNaoEncontradaException extends RuntimeException {
    public TurmaNaoEncontradaException (String mensagem) {
        super(mensagem);
    }
}
