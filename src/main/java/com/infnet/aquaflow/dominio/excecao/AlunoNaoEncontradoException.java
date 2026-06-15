package com.infnet.aquaflow.dominio.excecao;

public class AlunoNaoEncontradoException extends RuntimeException {
    public AlunoNaoEncontradoException (String mensagem) {
        super(mensagem);
    }
}
