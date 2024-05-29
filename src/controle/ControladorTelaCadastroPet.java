package controle;

import java.io.FileNotFoundException;
import java.io.IOException;
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
  private Button btnConfirmar, btnContinuar;
  @FXML
  private TextField campoRaca, campoNomePet, campoPeso, campoIdade;
  @FXML
  private ToggleGroup maisPet, especie;
  @FXML
  private RadioButton rbGato, rbCachorro, rbSim, rbNao;
  @FXML
  private Label labelStatus;

  private ControlePet cPet = new ControlePet();
  String especiePet;
  String maisPetValor;

  @Override
  public void initialize(URL url, ResourceBundle resources) {
    limparCampos();
    AnchorPaneConfirmacao.setVisible(false);
  }

  @FXML
  void Confirmar(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException {
    String nomePet = campoNomePet.getText();
    String raca = campoRaca.getText();
    int idade = 0;
    float peso = 0;

    if (nomePet.isEmpty() || raca.isEmpty() || campoIdade.getText().isEmpty() || campoPeso.getText().isEmpty()
        || especie.getSelectedToggle() == null || maisPet.getSelectedToggle() == null) {
      labelStatus.setText("Preencha todos os campos!");
      return;
    }
    if (!nomePet.matches("[a-zA-Z\\s]+")) {
      labelStatus.setText("O nome do pet deve conter apenas letras.");
      return;
    }
    if (!raca.matches("[a-zA-Z\\s]+")) {
      labelStatus.setText("a raça do pet deve conter apenas letras.");
      return;
    }
    if (!eInteiro(campoIdade.getText())) {
      labelStatus.setText("Entrada inválida para a idade. Por favor, insira um número válido.");
      return;
    } else {
      idade = Integer.parseInt(campoIdade.getText());
    }
    if (!eFlutuante(campoPeso.getText())) {
      labelStatus.setText("Entrada inválida para o peso. Por favor, insira um número válido.");
      return;
    } else {
      peso = Float.parseFloat(campoPeso.getText());
    }
    RadioButton especieSelecionada = (RadioButton) especie.getSelectedToggle();
    especiePet = especieSelecionada.getText(); // "Gato" ou "Cachorro"
    RadioButton maisPetSelecionada = (RadioButton) maisPet.getSelectedToggle();
    maisPetValor = maisPetSelecionada.getText(); // "Sim" ou "Não"

    cPet.cadastrarPet(nomePet, raca, idade, peso, especiePet);
    limparCampos();
    btnConfirmar.setDisable(true);
    AnchorPaneConfirmacao.setVisible(true);

  }

  @FXML
  void continuar(ActionEvent event) {
    if (maisPetValor.equals("SIM")) {
      gerenciador.getStage().close();
      gerenciador.trocarCena("/visao/fxml/TelaCadastroPet.fxml");
    } else {
      gerenciador.getStage().close();
      gerenciador.trocarCena("/visao/fxml/TelaInicial.fxml");
    }
  }

  private boolean eInteiro(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private boolean eFlutuante(String str) {
    try {
      Float.parseFloat(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  void limparCampos() {
    campoNomePet.setText("");
    campoRaca.setText("");
    campoIdade.setText("");
    campoPeso.setText("");
    especie.selectToggle(null);
    maisPet.selectToggle(null);
    labelStatus.setText("");
  }
}