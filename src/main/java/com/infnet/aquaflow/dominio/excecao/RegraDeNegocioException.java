package com.infnet.aquaflow.dominio.excecao;

public class RegraDeNegocioException extends RuntimeException {
    public RegraDeNegocioException (String mensagem) {
        super(mensagem);
    }
}
