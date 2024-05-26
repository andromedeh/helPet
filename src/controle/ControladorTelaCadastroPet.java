package controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaCadastroPet extends ControladorBase {
  @FXML
  private AnchorPane AnchorPaneTelaCadastroPet;
  @FXML
  private Button btnConfirmar;
  @FXML
  private TextField campoRaca, campoNomePet, campoPeso, campoIdade;
  @FXML
  private ToggleGroup maisPet, especie;
  @FXML
  private RadioButton rbGato, rbCachorro, rbSim, rbNao;

  @FXML
  void realizarCadastro(ActionEvent event) {

  }

  @FXML
  void Confirmar(ActionEvent event) {

  }

}
