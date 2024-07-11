package controle;

import java.net.URL;
import java.util.ResourceBundle;

import controle.controle_back.RemedioController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaCadastroRemedio extends ControladorBase implements Initializable {

  @FXML
  AnchorPane AnchorPaneTelaCadastroRemedio;

  @FXML
  Button btnCadastrarRemedio;

  @FXML
  TextField campoNomeRemedio;

  @FXML
  TextField campoDescricaoRemedio;

  @FXML
  Label labelStatusRemedio;

  String nomeRemedio;
  String descricaoRemedio;
  RemedioController remedio = new RemedioController();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    limparCamposRemedio();
  }

  @FXML
  void cadastrarRemedio(ActionEvent event) {
    nomeRemedio = campoNomeRemedio.getText();
    descricaoRemedio = campoDescricaoRemedio.getText();

    if (nomeRemedio.isEmpty() || descricaoRemedio.isEmpty()) {
      labelStatusRemedio.setText("Preencha todos os campos!");
      return;
    }

    if (!nomeRemedio.matches("[a-zA-Z\\s]+")) {
      labelStatusRemedio.setText("O nome do remédio deve conter apenas letras.");
      return;
    }
    if (!descricaoRemedio.matches("[a-zA-Z\\s]+")) {
      labelStatusRemedio.setText("A descrição do remédio deve conter apenas letras.");
      return;
    }

    remedio.cadastrarRemedio(nomeRemedio, descricaoRemedio);
    labelStatusRemedio.setText("Cadastrado");
    ;

    AnchorPaneTelaCadastroRemedio.setVisible(true);
    btnCadastrarRemedio.setDisable(true);
    btnCadastrarRemedio.setVisible(false);
    limparCamposRemedio();

  }

  void limparCamposRemedio() {
    campoNomeRemedio.setText("");
    campoDescricaoRemedio.setText("");
  }
}
