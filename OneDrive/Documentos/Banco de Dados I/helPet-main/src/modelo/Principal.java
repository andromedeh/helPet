package modelo;

import java.io.IOException;
import controle.GerenciadorCena;
import javafx.application.Application;
import javafx.stage.Stage;

public class Principal extends Application {
	private GerenciadorCena gerenciador;

	@Override
	public void start(Stage primaryStage) throws IOException {
		gerenciador = new GerenciadorCena(primaryStage);
		gerenciador.trocarCena("/visao/fxml/TelaInicial.fxml");
	}

	public static void main(String[] args) {
		launch(args);
	}

}