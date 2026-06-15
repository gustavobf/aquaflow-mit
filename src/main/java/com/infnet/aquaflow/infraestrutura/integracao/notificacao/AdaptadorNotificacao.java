package com.infnet.aquaflow.infraestrutura.integracao.notificacao;

import com.infnet.aquaflow.dominio.porta.saida.NotificacaoPorta;
import org.springframework.stereotype.Component;

@Component
public class AdaptadorNotificacao implements NotificacaoPorta {
    private final ClienteNotificacao clienteNotificacao;

    public AdaptadorNotificacao (ClienteNotificacao clienteNotificacao) {
        this.clienteNotificacao = clienteNotificacao;
    }

    @Override
    public void enviar (String destinatario, String mensagem) {
        clienteNotificacao.enviarMensagem(destinatario, mensagem);
    }
}
