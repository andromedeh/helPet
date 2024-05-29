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
    Image icon = new Image(getClass().getClassLoader().getResourceAsStream("visao/img/icon.png"));

    public GerenciadorCena(Stage stage) {
        this.stage = stage;
    }

    public void trocarCena(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setTitle("HelPet");
            stage.setResizable(false);
            Font.loadFont(getClass().getResourceAsStream("/visao/fontes/Quicksand_Bold.ttf"), 12.2);
            scene.getStylesheets().add("/visao/Estilo.css");
            stage.setOnCloseRequest(e -> {
                System.exit(0);
            });
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
