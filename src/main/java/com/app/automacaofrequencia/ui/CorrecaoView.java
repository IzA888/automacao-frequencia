package com.app.automacaofrequencia.ui;

import com.app.automacaofrequencia.model.Aluno;
import com.app.automacaofrequencia.service.CSVUtils;
import com.app.automacaofrequencia.service.CorretorProvas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class CorrecaoView {

    private MainApp app;
    private VBox layout = new VBox(10);
    private TableView<Aluno> tabela = new TableView<>();
    private ObservableList<Aluno> dados = FXCollections.observableArrayList();

    public CorrecaoView(MainApp app) {
        this.app = app;

        // Colunas da tabela
        TableColumn<Aluno, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Aluno, String> colMatricula = new TableColumn<>("Matrícula");
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));

        TableColumn<Aluno, Double> colNota = new TableColumn<>("Nota");
        colNota.setCellValueFactory(new PropertyValueFactory<>("nota"));

        tabela.getColumns().addAll(colNome, colMatricula, colNota);
        tabela.setItems(dados);

        // Botões
        Button btnImportar = new Button("Importar Respostas (CSV)");
        btnImportar.setOnAction(e -> importarCSV());

        Button btnCorrigir = new Button("Corrigir Provas");
        btnCorrigir.setOnAction(e -> corrigirProvas());

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> app.voltarMenu());

        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(tabela, btnImportar, btnCorrigir, btnVoltar);
    }

    public VBox getLayout() {
        return layout;
    }

    private void importarCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir arquivo de respostas");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                List<Aluno> alunos = CSVUtils.lerAlunos(file.getAbsolutePath());
                dados.setAll(alunos);
                mostrarAlerta("Sucesso", "Respostas importadas!");
            } catch (Exception ex) {
                mostrarAlerta("Erro", "Falha ao importar: " + ex.getMessage());
            }
        }
    }

    private void corrigirProvas() {
        if (app.getGabarito() == null) {
            mostrarAlerta("Erro", "Cadastre o gabarito primeiro!");
            return;
        }

        CorretorProvas corretor = new CorretorProvas();
        for (Aluno aluno : dados) {
            corretor.corrigirProvas(aluno, app.getGabarito());
        }

        app.setAlunosCorrigidos(dados);
        tabela.refresh();
        mostrarAlerta("Sucesso", "Provas corrigidas!");
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
