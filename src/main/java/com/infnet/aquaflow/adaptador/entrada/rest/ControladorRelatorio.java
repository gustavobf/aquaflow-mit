package com.infnet.aquaflow.adaptador.entrada.rest;

import com.infnet.aquaflow.adaptador.entrada.rest.documentacao.*;
import com.infnet.aquaflow.aplicacao.caso_de_uso.relatorio.*;
import com.infnet.aquaflow.aplicacao.servico.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/relatorios")
public class ControladorRelatorio implements ControladorRelatorioDocumentacao {
    private final GerarRelatorioPresencaTurmaCasoDeUso gerarRelatorioPresencaTurmaCasoDeUso;
    private final GerarRelatorioOcupacaoTurmasCasoDeUso gerarRelatorioOcupacaoTurmasCasoDeUso;
    private final GerarRelatorioPagamentoCasoDeUso gerarRelatorioPagamentoCasoDeUso;
    private final GeradorPdfRelatorio geradorPdfRelatorio;

    public ControladorRelatorio (GerarRelatorioPresencaTurmaCasoDeUso gerarRelatorioPresencaTurmaCasoDeUso,
                                 GerarRelatorioOcupacaoTurmasCasoDeUso gerarRelatorioOcupacaoTurmasCasoDeUso,
                                 GerarRelatorioPagamentoCasoDeUso gerarRelatorioPagamentoCasoDeUso,
                                 GeradorPdfRelatorio geradorPdfRelatorio) {
        this.gerarRelatorioPresencaTurmaCasoDeUso = gerarRelatorioPresencaTurmaCasoDeUso;
        this.gerarRelatorioOcupacaoTurmasCasoDeUso = gerarRelatorioOcupacaoTurmasCasoDeUso;
        this.gerarRelatorioPagamentoCasoDeUso = gerarRelatorioPagamentoCasoDeUso;
        this.geradorPdfRelatorio = geradorPdfRelatorio;
    }

    @GetMapping(value = "/presencas/turma/{turmaId}", produces = MediaType.APPLICATION_PDF_VALUE)
    @Override
    public ResponseEntity<byte[]> presencasPorTurma (@PathVariable UUID turmaId) {
        var relatorio = gerarRelatorioPresencaTurmaCasoDeUso.executar(turmaId);
        byte[] conteudoPdf = geradorPdfRelatorio.gerarPresencaTurma(relatorio);

        return respostaPdf("relatorio-presencas-turma-" + turmaId + ".pdf", conteudoPdf);
    }

    @GetMapping(value = "/turmas/ocupacao", produces = MediaType.APPLICATION_PDF_VALUE)
    @Override
    public ResponseEntity<byte[]> ocupacaoTurmas () {
        var relatorio = gerarRelatorioOcupacaoTurmasCasoDeUso.executar();
        byte[] conteudoPdf = geradorPdfRelatorio.gerarOcupacaoTurmas(relatorio);

        return respostaPdf("relatorio-ocupacao-turmas.pdf", conteudoPdf);
    }

    @GetMapping(value = "/pagamentos/resumo", produces = MediaType.APPLICATION_PDF_VALUE)
    @Override
    public ResponseEntity<byte[]> resumoPagamentos () {
        var relatorio = gerarRelatorioPagamentoCasoDeUso.gerarResumo();
        byte[] conteudoPdf = geradorPdfRelatorio.gerarResumoPagamentos(relatorio);

        return respostaPdf("relatorio-pagamentos-resumo.pdf", conteudoPdf);
    }

    @GetMapping(value = "/pagamentos/turmas", produces = MediaType.APPLICATION_PDF_VALUE)
    @Override
    public ResponseEntity<byte[]> pagamentosPorTurma () {
        var relatorio = gerarRelatorioPagamentoCasoDeUso.gerarPorTurma();
        byte[] conteudoPdf = geradorPdfRelatorio.gerarPagamentosPorTurma(relatorio);

        return respostaPdf("relatorio-pagamentos-por-turma.pdf", conteudoPdf);
    }

    private ResponseEntity<byte[]> respostaPdf (String nomeArquivo, byte[] conteudoPdf) {
        ContentDisposition contentDisposition = ContentDisposition.attachment().filename(nomeArquivo).build();

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString()).body(conteudoPdf);
    }
}

