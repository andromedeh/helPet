package controle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ControladorTelaEscolhaProfissional extends ControladorBase implements Initializable {
  @FXML
  private Button bntProfissional, btnSecretaria, btnVoltar;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

  @FXML
  void perfilMedico(ActionEvent event) {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaLoginProfissionalMedico.fxml");
  }

  @FXML
  void perfilSecretaria(ActionEvent event) {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaLoginProfissionalSecretaria.fxml");
  }

  @FXML
  void voltar(ActionEvent event) {
    // limparCampos();
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaInicial.fxml");
  }

}
