package controle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaInicial extends ControladorBase implements Initializable {
  @FXML
  private AnchorPane AnchorPaneTelaInicial;
  @FXML
  private Button btnEntrar, btnCadastrar;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

  @FXML
  void entrar(ActionEvent event) {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaEscolhaPerfil.fxml");
  }

  @FXML
  void cadastrarUsuario(ActionEvent event) {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaCadastroCliente.fxml");
  }
}
