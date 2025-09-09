package com.app.automacaofrequencia.ui;

import com.app.automacaofrequencia.model.Aluno;
import com.app.automacaofrequencia.service.AutomacaoNotas;
import com.app.automacaofrequencia.service.RelatorioService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

public class ResultadosView {

    private MainApp app;
    private VBox layout = new VBox(10);
    private TableView<Aluno> tabela = new TableView<>();
    private ObservableList<Aluno> dados = FXCollections.observableArrayList();

    public ResultadosView(MainApp app) {
        this.app = app;

        // Colunas
        TableColumn<Aluno, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Aluno, String> colMatricula = new TableColumn<>("Matrícula");
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));

        TableColumn<Aluno, Double> colNota = new TableColumn<>("Nota");
        colNota.setCellValueFactory(new PropertyValueFactory<>("nota"));

        tabela.getColumns().addAll(colNome, colMatricula, colNota);
        tabela.setItems(dados);

        // Carregar dados do MainApp
        if (app.getAlunosCorrigidos() != null) {
            dados.setAll(app.getAlunosCorrigidos());
        }

        // Botões
        Button btnCSV = new Button("Exportar CSV");
        btnCSV.setOnAction(e -> exportarCSV());

        Button btnPDF = new Button("Exportar PDF");
        btnPDF.setOnAction(e -> exportarPDF());

        Button btnSite = new Button("Lançar Notas no Site");
        btnSite.setOnAction(e -> lancarNotasNoSite());

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> app.voltarMenu());

        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(tabela, btnCSV, btnPDF, btnSite, btnVoltar);
    }

    public VBox getLayout() {
        return layout;
    }

    private void exportarCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                RelatorioService relatorio = new RelatorioService();
                relatorio.exportarCSV(dados, file.getAbsolutePath());
                mostrarAlerta("Sucesso", "CSV exportado!");
            } catch (Exception ex) {
                mostrarAlerta("Erro", "Falha ao exportar: " + ex.getMessage());
            }
        }
    }

    private void exportarPDF() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                RelatorioService relatorio = new RelatorioService();
                relatorio.exportarPDF(dados, file.getAbsolutePath());
                mostrarAlerta("Sucesso", "PDF exportado!");
            } catch (Exception ex) {
                mostrarAlerta("Erro", "Falha ao exportar: " + ex.getMessage());
            }
        }
    }

    private void lancarNotasNoSite() {
        try {
            AutomacaoNotas bot = new AutomacaoNotas();
            bot.lancarNotas(dados);
            mostrarAlerta("Sucesso", "Notas lançadas no site!");
        } catch (Exception ex) {
            mostrarAlerta("Erro", "Falha na automação: " + ex.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
