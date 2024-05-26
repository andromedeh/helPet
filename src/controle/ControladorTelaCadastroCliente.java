package controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaCadastroCliente extends ControladorBase {
  @FXML
  private AnchorPane AnchorPaneTelaCadastroCliente;
  @FXML
  private Button btnContinuar;
  @FXML
  private TextField campoNome, campoSobrenome, campoCpf, campoTelefone, campoEmail, campoEndereco,
      campoSenha, campoConfirmarSenha;

  @FXML
  void realizarCadastro(ActionEvent event) {

  }

  @FXML
  void Continuar(ActionEvent event) {
    gerenciador.trocarCena("/visao/fxml/TelaCadastroPet.fxml");
  }

}
