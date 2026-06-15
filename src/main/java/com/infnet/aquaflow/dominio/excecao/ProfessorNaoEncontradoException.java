package com.infnet.aquaflow.dominio.excecao;

public class ProfessorNaoEncontradoException extends RuntimeException {
    public ProfessorNaoEncontradoException (String mensagem) {
        super(mensagem);
    }
}
