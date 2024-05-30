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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaCadastroCliente extends ControladorBase implements Initializable {
  @FXML
  private AnchorPane AnchorPaneTelaCadastroCliente;
  @FXML
  private Button btnContinuar, btnVoltar;
  @FXML
  private TextField campoNome, campoSobrenome, campoCpf, campoTelefone, campoEmail, campoEndereco;
  @FXML
  private PasswordField campoSenha, campoConfirmarSenha;
  @FXML
  private Label labelStatus;

  private ControleCliente cCliente = new ControleCliente();

  @Override
  public void initialize(URL url, ResourceBundle resources) {
    limparCampos();
  }

  @FXML
  void Continuar(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException {
    String nome = campoNome.getText();
    String sobrenome = campoSobrenome.getText();
    long cpf = 0;
    long telefone = 0;
    String email = campoEmail.getText();
    String endereco = campoEndereco.getText();
    String senha = campoSenha.getText();
    String confirmarSenha = campoConfirmarSenha.getText();

    if (nome.isEmpty() || sobrenome.isEmpty() || campoCpf.getText().isEmpty() || campoTelefone.getText().isEmpty()
        || email.isEmpty() || endereco.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
      labelStatus.setText("Preencha todos os campos!");
      return;
    }
    if (!(eString(nome, sobrenome, endereco))) {
      labelStatus.setText("Os dados (nome, sobrenome e endereço) devem conter apenas letras!");
      return;
    }

    if (!eLong(campoCpf.getText()) || campoCpf.getLength() != 11) {
      labelStatus.setText("Entrada inválida para o CPF. Por favor, insira um número válido.");
      return;
    } else {
      cpf = Long.parseLong(campoCpf.getText());
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

    if (cCliente.procurarClienteCpf(cpf) != null) {
      labelStatus.setText("CPF já cadastrado!");
      campoCpf.setText("");
      return;
    }
    if (cCliente.procurarClienteTel(telefone) != null) {
      labelStatus.setText("Telefone já cadastrado!");
      campoTelefone.setText("");
      return;
    }
    if (cCliente.procurarClienteEmail(email) != null) {
      labelStatus.setText("E-mail já cadastrado!");
      campoEmail.setText("");
      return;
    }
    if (!(senha.equals(confirmarSenha))) {
      labelStatus.setText("Senhas diferentes!");
      return;
    }
    cCliente.cadastrarCliente(nome, sobrenome, cpf, telefone, email, endereco, senha, confirmarSenha);
    limparCampos();
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaCadastroPet.fxml");
  }

  private boolean eString(String str1, String str2, String str3) {
    if (str1.matches("[a-zA-Z\\s]+") && str2.matches("[a-zA-Z\\s]+") && str3.matches("[a-zA-Z\\s]+")) {
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

  void limparCampos() {
    campoNome.setText("");
    campoSobrenome.setText("");
    campoCpf.setText("");
    campoTelefone.setText("");
    campoEmail.setText("");
    campoEndereco.setText("");
    campoSenha.setText("");
    campoConfirmarSenha.setText("");
    labelStatus.setText("");
  }

  @FXML
  void voltar(ActionEvent event) {
    limparCampos();
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaInicial.fxml");
  }
}