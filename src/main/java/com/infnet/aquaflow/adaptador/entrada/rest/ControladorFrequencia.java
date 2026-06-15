package com.infnet.aquaflow.adaptador.entrada.rest;

import com.infnet.aquaflow.adaptador.entrada.rest.documentacao.ControladorFrequenciaDocumentacao;
import com.infnet.aquaflow.aplicacao.caso_de_uso.frequencia.BuscarFrequenciaCasoDeUso;
import com.infnet.aquaflow.aplicacao.caso_de_uso.frequencia.RegistrarFrequenciaCasoDeUso;
import com.infnet.aquaflow.aplicacao.dto.requisicao.FrequenciaRequisicao;
import com.infnet.aquaflow.aplicacao.dto.resposta.FrequenciaResposta;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/frequencias")
public class ControladorFrequencia implements ControladorFrequenciaDocumentacao {
    private final RegistrarFrequenciaCasoDeUso registrarFrequenciaCasoDeUso;
    private final BuscarFrequenciaCasoDeUso buscarFrequenciaCasoDeUso;

    public ControladorFrequencia (RegistrarFrequenciaCasoDeUso registrarFrequenciaCasoDeUso,
                                  BuscarFrequenciaCasoDeUso buscarFrequenciaCasoDeUso) {
        this.registrarFrequenciaCasoDeUso = registrarFrequenciaCasoDeUso;
        this.buscarFrequenciaCasoDeUso = buscarFrequenciaCasoDeUso;
    }

    @PostMapping
    @Override
    public FrequenciaResposta registrar (@Valid @RequestBody FrequenciaRequisicao requisicao) {
        return registrarFrequenciaCasoDeUso.executar(requisicao);
    }

    @GetMapping("/matricula/{matriculaId}")
    @Override
    public List<FrequenciaResposta> buscarPorMatricula (@PathVariable UUID matriculaId) {
        return buscarFrequenciaCasoDeUso.executar(matriculaId);
    }
}
