package com.app.automacaofrequencia.service;

import java.io.IOException;
import java.util.List;

import com.app.automacaofrequencia.model.Aluno;
import com.app.automacaofrequencia.service.CSVUtils;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class RelatorioService {
    public void exportarCorrecoesCSV(List<Aluno> alunos, String caminho) throws IOException {
        CSVUtils.escreverAlunos(alunos, caminho);
    }

    public void exportarCorrecoesPDF(List<Aluno> alunos, String caminho) throws IOException {
        PdfWriter writer = new PdfWriter(caminho);
        PdfDocument pdf = new PdfDocument(writer);
        Document documento = new Document(pdf);

        documento.add(new Paragraph("Relatório de Notas").setBold().setFontSize(16));

        Table table = new Table(3);
        table.addCell("Nome");
        table.addCell("Matrícula");
        table.addCell("Nota");

        for (Aluno aluno : alunos) {
            table.addCell(aluno.getNome());
            table.addCell(aluno.getMatricula());
            table.addCell(String.valueOf(aluno.getNota()));
        }

        documento.add(table);
        documento.close();
    }
    
}
