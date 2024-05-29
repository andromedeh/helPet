package controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaEscolhaPerfil extends ControladorBase {
  @FXML
  private AnchorPane AnchorPaneTelaEscolhaPerfil;
  @FXML
  private Button btnCliente, btnFuncionario, btnAdministrador;

  @FXML
  void perfilAdministrador(ActionEvent event) {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaLogin.fxml");
  }

  @FXML
  void perfilCliente(ActionEvent event) {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaLogin.fxml");
  }

  @FXML
  void perfilFuncionario(ActionEvent event) {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaLogin.fxml");
  }
}
