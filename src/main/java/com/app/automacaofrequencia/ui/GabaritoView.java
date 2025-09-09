package com.app.automacaofrequencia.ui;

import com.app.automacaofrequencia.model.Gabarito;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class GabaritoView {

    private MainApp app;
    private VBox layout = new VBox(10);

    public GabaritoView(MainApp app) {
        this.app = app;

        Label lblQtd = new Label("Número de questões:");
        TextField txtQtd = new TextField();
        Button btnGerar = new Button("Gerar Campos");

        VBox respostasBox = new VBox(5);
        List<TextField> campos = new ArrayList<>();

        btnGerar.setOnAction(e -> {
            respostasBox.getChildren().clear();
            campos.clear();
            try {
                int qtd = Integer.parseInt(txtQtd.getText());
                for (int i = 1; i <= qtd; i++) {
                    TextField campo = new TextField();
                    campo.setPromptText("Resposta Q" + i);
                    campos.add(campo);
                    respostasBox.getChildren().add(campo);
                }
            } catch (NumberFormatException ex) {
                mostrarAlerta("Erro", "Digite um número válido!");
            }
        });

        Button btnSalvar = new Button("Salvar Gabarito");
        btnSalvar.setOnAction(e -> {
            List<String> respostas = new ArrayList<>();
            for (TextField campo : campos) {
                if (campo.getText().isEmpty()) {
                    mostrarAlerta("Erro", "Preencha todas as respostas!");
                    return;
                }
                respostas.add(campo.getText().toUpperCase());
            }
            app.setGabarito(new Gabarito(respostas));
            mostrarAlerta("Sucesso", "Gabarito salvo!");
            app.voltarMenu();
        });

        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(lblQtd, txtQtd, btnGerar, respostasBox, btnSalvar);
    }

    public VBox getLayout() { return layout; }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
