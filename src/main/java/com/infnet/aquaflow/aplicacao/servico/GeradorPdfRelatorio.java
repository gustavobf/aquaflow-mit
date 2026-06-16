package com.infnet.aquaflow.aplicacao.servico;

import com.infnet.aquaflow.aplicacao.dto.resposta.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.math.*;
import java.time.*;
import java.time.format.*;
import java.util.List;

@Component
public class GeradorPdfRelatorio {
    private static final DateTimeFormatter FORMATO_DATA_HORA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public byte[] gerarPresencaTurma (RelatorioPresencaTurmaResposta relatorio) {
        return gerarDocumento("Relatorio de Presencas por Turma", documento -> {
            documento.add(new Paragraph("Turma: " + relatorio.nomeTurma()));
            documento.add(new Paragraph("ID da turma: " + relatorio.turmaId()));
            documento.add(new Paragraph("Total de registros: " + relatorio.totalRegistros()));
            documento.add(new Paragraph("Total de presencas: " + relatorio.totalPresencas()));
            documento.add(new Paragraph("Total de ausencias: " + relatorio.totalAusencias()));
            documento.add(
                    new Paragraph("Taxa de presenca (%): " + formatarDecimal(relatorio.taxaPresencaPercentual())));
        });
    }

    public byte[] gerarOcupacaoTurmas (List<RelatorioTurmaOcupacaoResposta> relatorios) {
        return gerarDocumento("Relatorio de Ocupacao das Turmas", documento -> {
            PdfPTable tabela = criarTabela(new String[]{"Turma", "Nivel", "Capacidade", "Ativas", "Vagas", "Taxa (%)"});

            for (RelatorioTurmaOcupacaoResposta relatorio : relatorios) {
                tabela.addCell(relatorio.nomeTurma());
                tabela.addCell(relatorio.nivelTurma());
                tabela.addCell(String.valueOf(relatorio.capacidadeMaxima()));
                tabela.addCell(String.valueOf(relatorio.matriculasAtivas()));
                tabela.addCell(String.valueOf(relatorio.vagasDisponiveis()));
                tabela.addCell(formatarDecimal(relatorio.taxaOcupacaoPercentual()));
            }

            documento.add(tabela);
        });
    }

    public byte[] gerarResumoPagamentos (RelatorioPagamentoResumoResposta relatorio) {
        return gerarDocumento("Relatorio de Pagamentos - Resumo", documento -> {
            documento.add(new Paragraph("Matriculas ativas: " + relatorio.quantidadeMatriculasAtivas()));
            documento.add(new Paragraph("Matriculas canceladas: " + relatorio.quantidadeMatriculasCanceladas()));
            documento.add(
                    new Paragraph("Valor mensalidade ativa: " + formatarDecimal(relatorio.valorMensalidadeAtiva())));
            documento.add(new Paragraph(
                    "Valor mensalidade cancelada: " + formatarDecimal(relatorio.valorMensalidadeCancelada())));
            documento.add(
                    new Paragraph("Valor mensalidade total: " + formatarDecimal(relatorio.valorMensalidadeTotal())));
        });
    }

    public byte[] gerarPagamentosPorTurma (List<RelatorioPagamentoTurmaResposta> relatorios) {
        return gerarDocumento("Relatorio de Pagamentos por Turma", documento -> {
            PdfPTable tabela = criarTabela(new String[]{"Turma", "Ativas", "Canceladas", "Receita ativa"});

            for (RelatorioPagamentoTurmaResposta relatorio : relatorios) {
                tabela.addCell(relatorio.nomeTurma());
                tabela.addCell(String.valueOf(relatorio.quantidadeMatriculasAtivas()));
                tabela.addCell(String.valueOf(relatorio.quantidadeMatriculasCanceladas()));
                tabela.addCell(formatarDecimal(relatorio.receitaMensalAtiva()));
            }

            documento.add(tabela);
        });
    }

    private byte[] gerarDocumento (String titulo, BlocoDocumento blocoDocumento) {
        try {
            ByteArrayOutputStream streamSaida = new ByteArrayOutputStream();
            Document documento = new Document();
            PdfWriter.getInstance(documento, streamSaida);
            documento.open();

            Font tituloFonte = new Font(Font.HELVETICA, 16, Font.BOLD);
            documento.add(new Paragraph(titulo, tituloFonte));
            documento.add(new Paragraph("Gerado em: " + LocalDateTime.now().format(FORMATO_DATA_HORA)));
            documento.add(new Paragraph(" "));

            blocoDocumento.adicionar(documento);

            documento.close();
            return streamSaida.toByteArray();
        } catch (DocumentException excecao) {
            throw new IllegalStateException("Falha ao gerar arquivo PDF do relatorio.", excecao);
        }
    }

    private PdfPTable criarTabela (String[] cabecalhos) {
        PdfPTable tabela = new PdfPTable(cabecalhos.length);
        tabela.setWidthPercentage(100);

        Font cabecalhoFonte = new Font(Font.HELVETICA, 10, Font.BOLD);
        for (String cabecalho : cabecalhos) {
            PdfPCell celula = new PdfPCell(new Phrase(cabecalho, cabecalhoFonte));
            tabela.addCell(celula);
        }

        return tabela;
    }

    private String formatarDecimal (BigDecimal valor) {
        return valor == null ? "0.00" : valor.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

    @FunctionalInterface
    private interface BlocoDocumento {
        void adicionar (Document documento) throws DocumentException;
    }
}

