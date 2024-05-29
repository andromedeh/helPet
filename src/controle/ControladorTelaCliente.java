package controle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaCliente extends ControladorBase implements Initializable {

  @FXML
  private Button btnVoltar;
  @FXML
  private AnchorPane AnchorPaneTelaCliente;

  @Override
  public void initialize(URL url, ResourceBundle resources) {
  }

  @FXML
  void voltar(ActionEvent event) {
    gerenciador.trocarCena("/visao/fxml/TelaInicial.fxml");
  }
}
