package com.infnet.aquaflow.aplicacao.caso_de_uso.matricula;

import com.infnet.aquaflow.aplicacao.dto.resposta.MatriculaResposta;
import com.infnet.aquaflow.aplicacao.mapeador.MatriculaMapeador;
import com.infnet.aquaflow.dominio.entidade.Matricula;
import com.infnet.aquaflow.dominio.excecao.RegraDeNegocioException;
import com.infnet.aquaflow.dominio.porta.saida.MatriculaRepositorio;
import com.infnet.aquaflow.dominio.servico.ServicoMatricula;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CancelarMatriculaCasoDeUso {
    private final MatriculaRepositorio matriculaRepositorio;
    private final ServicoMatricula servicoMatricula;

    public CancelarMatriculaCasoDeUso (MatriculaRepositorio matriculaRepositorio, ServicoMatricula servicoMatricula) {
        this.matriculaRepositorio = matriculaRepositorio;
        this.servicoMatricula = servicoMatricula;
    }

    public MatriculaResposta executar (UUID matriculaId) {
        Matricula existente = matriculaRepositorio.buscarPorId(matriculaId)
                .orElseThrow(() -> new RegraDeNegocioException("Matricula nao encontrada."));
        Matricula cancelada = servicoMatricula.cancelar(existente);
        return MatriculaMapeador.paraResposta(cancelada);
    }
}
