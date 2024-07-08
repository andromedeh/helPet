package controle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaCadastroProfissionais extends ControladorBase implements Initializable{

  @FXML
  private AnchorPane AnchorPaneCadastroProfissionais;

  @FXML
  private Button btnConfirmar;

  @FXML
  private TextField campoNome, campoSobrenome, campoCpf, campoTelefone, campoEmailCadastro, campoCRMV;

  @FXML
  private PasswordField campoSenha, campoConfirmarSenha;

  @FXML
  private Label labelStatus, labelCRMV;

  @FXML
  private RadioButton rbSecretario, rbMedico;

  @FXML
  private ToggleGroup funcao;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    labelStatus.setText("");
    limparCampos();
  }

  @FXML
  void confirmar(ActionEvent event) {
    String nome = campoNome.getText();
    String sobrenome = campoSobrenome.getText();
    long cpf = 0;
    long telefone = 0;
    long CRMV = 0;
    String email = campoEmailCadastro.getText();
    String senha = campoSenha.getText();
    String confirmarSenha = campoConfirmarSenha.getText();

    if (nome.isEmpty() || sobrenome.isEmpty() || campoCpf.getText().isEmpty() || campoTelefone.getText().isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty() || funcao.getSelectedToggle() == null) {
      labelStatus.setText("Preencha todos os campos!");
      return;
    }else{
      if (rbMedico.isSelected()){
        if (campoCRMV.getText().isEmpty()){
          labelStatus.setText("Preencha todos os campos!");
          return;
        }
      }
    }

    if (!(eString(nome, sobrenome))) {
      labelStatus.setText("Os dados (nome, sobrenome) devem conter apenas letras!");
      return;
    }

    if (!eLong(campoCpf.getText()) || campoCpf.getLength() != 11) {
      labelStatus.setText("Entrada inválida para o CPF. Por favor, insira um número válido.");
      return;
    } else {
      cpf = Long.parseLong(campoCpf.getText());
    }

    if (rbMedico.isSelected()){
      if (!eLong(campoCRMV.getText())) {
        labelStatus.setText("Entrada inválida para o CRMV. Por favor, insira um número válido.");
        return;
      } else {
        CRMV = Long.parseLong(campoCRMV.getText());
      }
    }

    if (!eLong(campoTelefone.getText()) || campoTelefone.getLength() != 11) {
      labelStatus.setText("Entrada inválida para o telefone. Por favor, insira um número válido.");
      return;
    } else {
      telefone = Long.parseLong(campoTelefone.getText());
    }

    if (!email.toLowerCase().endsWith("@gmail.com")) {
      labelStatus.setText("O e-mail deve ser do domínio '@gmail.com'");
      return;
    }

    if (!(senha.equals(confirmarSenha))) {
      labelStatus.setText("Senhas diferentes!");
      return;
    }

    // CADASTRAR PROFISSIONAL NO BD: FALTA IMPLEMENTAR
    if (rbMedico.isSelected()){
      // CADASTRAR MEDICO
    } else {
      // CADASTRAR SECRETARIO
    }
    
    labelStatus.setText("Cadastro finalizado!");
    limparCampos();
  }

  @FXML
  void mostrarCampoCRMV(ActionEvent event) {
    if (rbMedico.isSelected()){
      labelCRMV.setVisible(true);
      campoCRMV.setVisible(true);
    } else {
      labelCRMV.setVisible(false);
      campoCRMV.setVisible(false);
    }
  }

  public void limparCampos(){
    campoNome.setText("");
    campoSobrenome.setText("");
    campoCpf.setText("");
    campoTelefone.setText("");
    campoEmailCadastro.setText("");
    campoSenha.setText("");
    campoConfirmarSenha.setText("");
    rbMedico.setSelected(false);
    rbSecretario.setSelected(false);
    labelCRMV.setVisible(false);
    campoCRMV.setVisible(false);
  }
  
  private boolean eString(String str1, String str2) {
    if (str1.matches("[a-zA-Z\\s]+") && str2.matches("[a-zA-Z\\s]+")) {
      return true;
    } else {
      return false;
    }
  }

  private boolean eLong(String l) {
    try {
      Long.parseLong(l);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
