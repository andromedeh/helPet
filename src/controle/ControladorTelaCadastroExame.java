package controle;

import java.net.URL;
import java.util.ResourceBundle;

import controle.controle_back.ExameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaCadastroExame extends ControladorBase implements Initializable {
  
  @FXML
  AnchorPane AnchorPaneTelaCadastroExame;
  
  @FXML
  Button btnCadastrarExame;

  @FXML
  TextField campoNomeExame;

  @FXML
  Label labelStatusExame;

  @FXML
  TextField campoDescricaoExame;

  String nomeExame;
  String descricaoExame;
  ExameController exame = new ExameController();
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    AnchorPaneTelaCadastroExame.setVisible(false);
  }

  @FXML
  void confirmar(ActionEvent event) {
    nomeExame = campoNomeExame.getText();
    descricaoExame = campoDescricaoExame.getText();

    if (nomeExame.isEmpty() || descricaoExame.isEmpty()) {
      labelStatusExame.setText("Preencha todos os campos!");
      return;
    }

    if (!nomeExame.matches("[a-zA-Z\\s]+")) {
      labelStatusExame.setText("O nome do pet deve conter apenas letras.");
      return;
    }
    if (!descricaoExame.matches("[a-zA-Z\\s]+")) {
      labelStatusExame.setText("a raça do pet deve conter apenas letras.");
      return;
    }

    exame.cadastrarExame(nomeExame, descricaoExame);

    AnchorPaneTelaCadastroExame.setVisible(true);
    btnCadastrarExame.setDisable(true);
    btnCadastrarExame.setVisible(false);
    limparCamposExame();
  }

  void limparCamposExame() {
    campoNomeExame.setText("");
    campoDescricaoExame.setText("");
  }
}
