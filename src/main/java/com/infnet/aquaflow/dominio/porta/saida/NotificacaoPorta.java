package com.infnet.aquaflow.dominio.porta.saida;

public interface NotificacaoPorta {
    void enviar (String destinatario, String mensagem);
}
