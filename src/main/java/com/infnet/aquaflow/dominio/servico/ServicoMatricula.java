package com.infnet.aquaflow.dominio.servico;

import com.infnet.aquaflow.aplicacao.dto.requisicao.MatriculaRequisicao;
import com.infnet.aquaflow.dominio.entidade.Matricula;
import com.infnet.aquaflow.dominio.estrategia.pagamento.EstrategiaPagamento;
import com.infnet.aquaflow.dominio.estrategia.pagamento.FabricaEstrategiaPagamento;
import com.infnet.aquaflow.dominio.excecao.RegraDeNegocioException;
import com.infnet.aquaflow.dominio.porta.saida.AlunoRepositorio;
import com.infnet.aquaflow.dominio.porta.saida.MatriculaRepositorio;
import com.infnet.aquaflow.dominio.porta.saida.PagamentoPorta;
import com.infnet.aquaflow.dominio.porta.saida.TurmaRepositorio;
import org.springframework.stereotype.Service;

@Service
public class ServicoMatricula {
    private final AlunoRepositorio alunoRepositorio;
    private final TurmaRepositorio turmaRepositorio;
    private final MatriculaRepositorio matriculaRepositorio;
    private final PagamentoPorta pagamentoPorta;
    private final FabricaEstrategiaPagamento fabricaEstrategiaPagamento;

    public ServicoMatricula (AlunoRepositorio alunoRepositorio, TurmaRepositorio turmaRepositorio,
                             MatriculaRepositorio matriculaRepositorio, PagamentoPorta pagamentoPorta,
                             FabricaEstrategiaPagamento fabricaEstrategiaPagamento) {
        this.alunoRepositorio = alunoRepositorio;
        this.turmaRepositorio = turmaRepositorio;
        this.matriculaRepositorio = matriculaRepositorio;
        this.pagamentoPorta = pagamentoPorta;
        this.fabricaEstrategiaPagamento = fabricaEstrategiaPagamento;
    }

    public Matricula matricular (MatriculaRequisicao requisicao) {
        alunoRepositorio.buscarPorId(requisicao.alunoId())
                .orElseThrow(() -> new RegraDeNegocioException("Aluno inexistente."));
        turmaRepositorio.buscarPorId(requisicao.turmaId())
                .orElseThrow(() -> new RegraDeNegocioException("Turma inexistente."));
        EstrategiaPagamento estrategia = fabricaEstrategiaPagamento.criar(requisicao.tipoPagamento());
        pagamentoPorta.cobrar(requisicao.valorMensalidade(), estrategia.tipo());
        Matricula matricula = Matricula.nova(requisicao.alunoId(), requisicao.turmaId(), requisicao.valorMensalidade());
        return matriculaRepositorio.salvar(matricula);
    }

    public Matricula cancelar (Matricula matricula) {
        return matriculaRepositorio.salvar(matricula.cancelar());
    }
}
