package controle;

import java.net.URL;
import java.util.ResourceBundle;

import controle.controle_back.VacinaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaCadastroVacina extends ControladorBase implements Initializable {
  @FXML
  private AnchorPane AnchorPaneTelaCadastroVacina;

  @FXML
  private Button btnCadastrarVacina;

  @FXML
  private TextField campoNomeVacina, campoTempoReforco;

  @FXML
  private Label labelStatusVacina;

  String nome;
  String tempoReforco;

  VacinaController vacina = new VacinaController();

  @FXML
  void cadastrarVacina(ActionEvent event) {

    nome = campoNomeVacina.getText();
    tempoReforco = campoTempoReforco.getText();

    if (nome.isEmpty() || tempoReforco.isEmpty()) {
      labelStatusVacina.setText("Preencha todos os campos!");
      return;
    }

    if (!nome.matches("[a-zA-Z\\s]+")) {
      labelStatusVacina.setText("O nome da vacina deve conter apenas letras.");
      return;
    }

    vacina.cadastrarVacina(nome, tempoReforco);
    labelStatusVacina.setText("Cadastrado");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    limparCampos();
  }

  public void limparCampos() {
    campoNomeVacina.clear();
    campoTempoReforco.clear();
    labelStatusVacina.setText("");
  }
}
