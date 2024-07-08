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
  private Button btnCliente, btnProfissional, btnAdministrador;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

  @FXML
  void entrar(ActionEvent event) {
    if (event.getSource().equals(btnCliente)) {
      gerenciador.getStage().close();
      gerenciador.trocarCena("/visao/fxml/TelaEscolhaCliente.fxml");
    }
    if (event.getSource().equals(btnProfissional)) {
      gerenciador.getStage().close();
      gerenciador.trocarCena("/visao/fxml/TelaEscolhaProfissional.fxml");
    }
    if (event.getSource().equals(btnAdministrador)) {
      gerenciador.getStage().close();
      gerenciador.trocarCena("/visao/fxml/TelaLoginAdministrador.fxml");
    }
  }
}
