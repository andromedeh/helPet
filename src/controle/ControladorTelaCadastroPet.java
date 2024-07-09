package controle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaCadastroPet extends ControladorBase implements Initializable {
  @FXML
  private AnchorPane AnchorPaneTelaCadastroPet, AnchorPaneConfirmacao;

  @FXML
  private TextField campoNomePet, campoRaca, campoIdade, campoPeso;

  @FXML
  private RadioButton rbGato, rbCachorro;

  @FXML
  private ToggleGroup especie;

  @FXML
  private Button btnConfirmar;

  @FXML
  private Label labelStatusPet;

  String especiePet;
  String nomePet;
  String raca;
  float idade;
  float peso;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    AnchorPaneConfirmacao.setVisible(false);
  }

  @FXML
  void confirmar(ActionEvent event) {
    nomePet = campoNomePet.getText();
    raca = campoRaca.getText();
    idade = 0;
    peso = 0;

    if (nomePet.isEmpty() || raca.isEmpty() || campoIdade.getText().isEmpty() || campoPeso.getText().isEmpty()
        || especie.getSelectedToggle() == null) {
      labelStatusPet.setText("Preencha todos os campos!");
      return;
    }

    if (!nomePet.matches("[a-zA-Z\\s]+")) {
      labelStatusPet.setText("O nome do pet deve conter apenas letras.");
      return;
    }
    if (!raca.matches("[a-zA-Z\\s]+")) {
      labelStatusPet.setText("a raça do pet deve conter apenas letras.");
      return;
    }
    if (!eFlutuante(campoIdade.getText())) {
      labelStatusPet.setText("Entrada inválida para a idade. Por favor, insira um número válido.");
      return;
    } else {
      idade = Float.parseFloat(campoIdade.getText());
    }
    if (!eFlutuante(campoPeso.getText())) {
      labelStatusPet.setText("Entrada inválida para o peso. Por favor, insira um número válido.");
      return;
    } else {
      peso = Float.parseFloat(campoPeso.getText());
    }

    RadioButton especieSelecionada = (RadioButton) especie.getSelectedToggle();
    especiePet = especieSelecionada.getText(); // "Gato" ou "Cachorro"

    // IMPLEMENTAR LOGICA PARA SALVAR OS DADOS DO CLIENTE E DO PET NO BANCO DE DADOS

    AnchorPaneConfirmacao.setVisible(true);
    btnConfirmar.setDisable(true);
    btnConfirmar.setVisible(false);
    limparCamposPet();

  }

  private boolean eFlutuante(String str) {
    try {
      Float.parseFloat(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  void limparCamposPet() {
    campoNomePet.setText("");
    campoRaca.setText("");
    campoIdade.setText("");
    campoPeso.setText("");
    especie.selectToggle(null);
  }
}
