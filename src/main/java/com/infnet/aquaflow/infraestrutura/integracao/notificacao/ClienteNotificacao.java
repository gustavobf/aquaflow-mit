package com.infnet.aquaflow.infraestrutura.integracao.notificacao;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ClienteNotificacao {
    private final List<NotificacaoFake> notificacoes = new CopyOnWriteArrayList<>();

    public void enviarMensagem (String destinatario, String mensagem) {
        notificacoes.add(new NotificacaoFake(UUID.randomUUID(), destinatario, mensagem, Instant.now()));
    }

    public List<NotificacaoFake> listarNotificacoes () {
        return List.copyOf(notificacoes);
    }

    public record NotificacaoFake(UUID id, String destinatario, String mensagem, Instant criadoEm) {
    }
}
