package controle;

import java.net.URL;
import java.util.ResourceBundle;

import controle.controle_back.ClienteController;
import controle.controle_back.MedicoVeterinarioController;
import controle.controle_back.ProfissionalController;
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

public class ControladorTelaCadastroProfissionais extends ControladorBase implements Initializable {

  @FXML
  private AnchorPane AnchorPaneCadastroProfissionais;

  @FXML
  private Button btnConfirmar;

  @FXML
  private TextField campoNome, campoSobrenome, campoCpf, campoTelefone, campoEmailCadastro, campoProfissionalCRMV;

  @FXML
  private PasswordField campoSenha, campoConfirmarSenha;

  @FXML
  private Label labelStatus, labelCRMV;

  @FXML
  private RadioButton rbSecretario, rbMedico;

  @FXML
  private ToggleGroup funcao;

  MedicoVeterinarioController medico = new MedicoVeterinarioController();
  ProfissionalController profissional = new ProfissionalController();
  ClienteController cliente = new ClienteController();

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
    int CRMV = 0;
    String email = campoEmailCadastro.getText();
    String senha = campoSenha.getText();
    String confirmarSenha = campoConfirmarSenha.getText();

    if (nome.isEmpty() || sobrenome.isEmpty() || campoCpf.getText().isEmpty() || campoTelefone.getText().isEmpty()
        || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty() || funcao.getSelectedToggle() == null) {
      labelStatus.setText("Preencha todos os campos!");
      return;
    } else {
      if (rbMedico.isSelected()) {
        if (campoProfissionalCRMV.getText().isEmpty()) {
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

    if (rbMedico.isSelected()) {
      if (!eInteiro(campoProfissionalCRMV.getText())) {
        labelStatus.setText("Entrada inválida para o CRMV. Por favor, insira um número válido.");
        return;
      } else {
        CRMV = Integer.parseInt(campoProfissionalCRMV.getText());
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

    RadioButton funcaoSelecionada = (RadioButton) funcao.getSelectedToggle();
    String funcaoCliente = funcaoSelecionada.getText();

    if (profissional.pesquisarProfissionals(cpf) != null || cliente.pesquisarCliente(cpf) != null) {
      labelStatus.setText("CPF já cadastrado!");
      return;
    }
    if (medico.pesquisarMedicoVeterinario(CRMV) != null) {
      labelStatus.setText("CRMV já cadastrado!");
      return;
    }

    // CADASTRAR PROFISSIONAL NO BD: FALTA IMPLEMENTAR.... OK!
    if (rbMedico.isSelected()) {
      medico.cadastrarMedicoVeterinario(nome, cpf, telefone, funcaoCliente, email, senha, CRMV);
    } else {
      // TALVEZ CRIAR A TABELA SECRETARIO E ALTERAR AQUI
      profissional.cadastrarProfissional(nome, cpf, telefone, funcaoCliente, email, senha);
    }

    labelStatus.setText("Cadastro finalizado!");
    limparCampos();
  }

  @FXML
  void mostrarCampoCRMV(ActionEvent event) {
    if (rbMedico.isSelected()) {
      labelCRMV.setVisible(true);
      campoProfissionalCRMV.setVisible(true);
    } else {
      labelCRMV.setVisible(false);
      campoProfissionalCRMV.setVisible(false);
    }
  }

  public void limparCampos() {
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
    campoProfissionalCRMV.setVisible(false);
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

  private boolean eInteiro(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
