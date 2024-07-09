package controle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaEscolhaCliente extends ControladorBase implements Initializable {
  @FXML
  private Button bntEntrar, btnCadastrar, btnVoltar;
  @FXML
  private AnchorPane anchorPaneEscolhaCliente;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

  @FXML
  void cadastrar(ActionEvent event) {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaCadastroCliente.fxml");
  }

  @FXML
  void entrar(ActionEvent event) {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaLoginCliente.fxml");
  }

  @FXML
  void voltar(ActionEvent event) {
    // limparCampos();
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaInicial.fxml");
  }

}
