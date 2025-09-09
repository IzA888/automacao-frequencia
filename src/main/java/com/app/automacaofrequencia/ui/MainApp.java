

import com.app.automacaofrequencia.model.Aluno;
import com.app.automacaofrequencia.model.Gabarito;
import com.app.automacaofrequencia.ui.GabaritoView;
import com.app.automacaofrequencia.ui.ResultadosView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.com.app.automacaofrequencia.ui.CorrecaoView;

import java.util.ArrayList;
import java.util.List;


public class MainApp extends Application {

    private Stage stagePrincipal;
    private Gabarito gabarito;
    private List<Aluno> alunosCorrigidos = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        this.stagePrincipal = stage;
        mostrarMenuPrincipal();
    }

    private void mostrarMenuPrincipal() {
        Button btnGabarito = new Button("Cadastrar Gabarito");
        btnGabarito.setOnAction(e -> {
            GabaritoView view = new GabaritoView(this);
            stagePrincipal.setScene(new Scene(view.getLayout(), 400, 500));
        });

        Button btnCorrecao = new Button("Corrigir Provas");
        btnCorrecao.setOnAction(e -> {
            CorrecaoView view = new CorrecaoView(this);
            stagePrincipal.setScene(new Scene(view.getLayout(), 600, 400));
        });

        Button btnResultados = new Button("Ver Resultados");
        btnResultados.setOnAction(e -> {
            ResultadosView view = new ResultadosView(this);
            stagePrincipal.setScene(new Scene(view.getLayout(), 600, 400));
        });

        VBox menu = new VBox(15, btnGabarito, btnCorrecao, btnResultados);
        Scene cena = new Scene(menu, 300, 200);
        stagePrincipal.setScene(cena);
        stagePrincipal.setTitle("Sistema de Correção de Provas");
        stagePrincipal.show();
    }

    // Getters e setters para compartilhar dados entre telas
    public void setGabarito(Gabarito g) { this.gabarito = g; }
    public Gabarito getGabarito() { return gabarito; }

    public void setAlunosCorrigidos(List<Aluno> lista) { this.alunosCorrigidos = lista; }
    public List<Aluno> getAlunosCorrigidos() { return alunosCorrigidos; }

    public void voltarMenu() {
        mostrarMenuPrincipal();
    }
    public static void main(String[] args) {
        launch();
    }
}
