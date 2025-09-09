

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.w3c.dom.Document;

import com.app.automacaofrequencia.model.Aluno;
import com.app.automacaofrequencia.service.CSVUtils;

public class RelatorioService {
    public void exportarCorrecoesCSV(List<Aluno> alunos, String caminho) throws IOException {
        CSVUtils.escreverAlunos(alunos, caminho);
    }

    public void exportarCorrecoesPDF(List<Aluno> alunos, String caminho) throws IOExeption {
        FileWriter writer = new FileWriter(caminho);
        Document documento = new Document(pdf);

        document.add(new Paragraph("Relatório de Notas").setBold().setFontSize(16));

        Table table = new Table(3);
        table.addCell("Nome");
        table.addCell("Matrícula");
        table.addCell("Nota");

        for (Aluno aluno : alunos) {
            table.addCell(aluno.getNome());
            table.addCell(aluno.getMatricula());
            table.addCell(String.valueOf(aluno.getNota()));
        }

        document.add(table);
        document.close();
    }
    
}
