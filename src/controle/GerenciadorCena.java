package controle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;

public class GerenciadorCena {
    private Stage stage;
    Image icon = new Image(getClass().getClassLoader().getResourceAsStream("visao/img/icon.png")); // busca o icone do
                                                                                                   // programa

    public GerenciadorCena(Stage stage) {
        this.stage = stage;
    }

    public void trocarCena(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.getIcons().add(icon); // troca icone
            stage.setScene(scene); // define a cena
            stage.setTitle("HelPet"); // define titulo
            stage.setResizable(false); // nao redimensionar a tela
            Font.loadFont(getClass().getResourceAsStream("/visao/fontes/Quicksand_Bold.ttf"), 12.2); // adcionando fonte
            scene.getStylesheets().add("/visao/Estilo.css"); // adicionando folhas de
            // estilo css
            stage.setOnCloseRequest(e -> { // encerrar corretamente
                System.exit(0);
            });

            // Obtém o controlador associado à nova cena e define o gerenciador de cena como
            // seu proprietário
            ControladorBase controller = loader.getController();
            controller.setGerenciador(this);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getStage() {
        return stage;
    }
}
