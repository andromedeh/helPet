package controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controle.controle_back.ClienteController;
import controle.controle_back.MedicoVeterinarioController;
import controle.controle_back.PetController;
import controle.controle_back.ProfissionalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControladorTelaCadastroCliente extends ControladorBase implements Initializable {
  /*
   * Nessa classe, 3 telas estão embutidas: A tela de cadastro do cliente, que
   * começa visível
   * a tela de cadastro do pet, que aparece quando se continua o cadastro do
   * cliente e a tela fxml
   * de confirmação de cadastro do pet, que pode ser seguida pela tela login (se o
   * cliente marcou NAO para cadastrar mais pets)
   * evoltar pra tela de cadastro pet (se o cliente marcou SIM para cadastrar mais
   * pets)
   */

  @FXML
  private AnchorPane AnchorPaneTelaCadastroCliente;
  // informacoes/atributos do cliente
  @FXML
  private TextField campoNome, campoSobrenome, campoCpf, campoTelefone, campoEmailCadastro, campoEndereco;

  @FXML
  private PasswordField campoSenha, campoConfirmarSenha;

  // botoes e labels
  @FXML
  private Button btnContinuar, bntVoltar;
  @FXML
  private Label labelStatusCliente;
  // DADOS CLIENTE
  String nome;
  String sobrenome;
  long cpf;
  long telefone;
  String email;
  String endereco;
  String senha;
  String confirmarSenha;

  ProfissionalController profissional = new ProfissionalController();
  MedicoVeterinarioController medicoVeterinario = new MedicoVeterinarioController();
  ClienteController cliente = new ClienteController();
  PetController pet = new PetController();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    limparCamposCliente();
  }

  // confirma o cadastro do cliente e continua pro cadastro do pet
  @FXML
  void continuar(ActionEvent event) {
    nome = campoNome.getText();
    sobrenome = campoSobrenome.getText();
    cpf = 0;
    telefone = 0;
    email = campoEmailCadastro.getText();
    endereco = campoEndereco.getText();
    senha = campoSenha.getText();
    confirmarSenha = campoConfirmarSenha.getText();

    if (nome.isEmpty() || sobrenome.isEmpty() || campoCpf.getText().isEmpty() || campoTelefone.getText().isEmpty()
        || email.isEmpty() || endereco.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
      labelStatusCliente.setText("Preencha todos os campos!");
      return;
    }

    if (!(eString(nome, sobrenome))) {
      labelStatusCliente.setText("Os dados (nome, sobrenome) devem conter apenas letras!");
      return;
    }

    if (!eLong(campoCpf.getText()) || campoCpf.getLength() != 11) {
      labelStatusCliente.setText("Entrada inválida para o CPF. Por favor, insira um número válido.");
      return;
    } else {
      cpf = Long.parseLong(campoCpf.getText());
    }

    if (!eLong(campoTelefone.getText()) || campoTelefone.getLength() != 11) {
      labelStatusCliente.setText("Entrada inválida para o telefone. Por favor, insira um número válido.");
      return;
    } else {
      telefone = Long.parseLong(campoTelefone.getText());
    }

    if (!email.toLowerCase().endsWith("@gmail.com")) {
      labelStatusCliente.setText("O e-mail deve ser do domínio '@gmail.com'");
      return;
    }

    if (!(senha.equals(confirmarSenha))) {
      labelStatusCliente.setText("Senhas diferentes!");
      return;
    }

    if (profissional.pesquisarProfissionais(cpf) != null || cliente.pesquisarCliente(cpf) != null) {
      labelStatusCliente.setText("CPF já existente!");
      return;
    }

    cliente.cadastrarCliente(nome, sobrenome, cpf, telefone, email, endereco, senha);

    AnchorPane anchorPane;
    try {
      anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/fxml/TelaCadastroPet.fxml"));
      Stage secondStage = new Stage();
      Scene secondScene = new Scene(anchorPane);
      secondStage.setScene(secondScene);
      secondStage.showAndWait();
    } catch (IOException ex) {
    }

    voltar(event);
  }

  @FXML
  void voltar(ActionEvent event) {
    limparCamposCliente();
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaEscolhaCliente.fxml");
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

  void limparCamposCliente() {
    campoNome.setText("");
    campoSobrenome.setText("");
    campoCpf.setText("");
    campoTelefone.setText("");
    campoEmailCadastro.setText("");
    campoEndereco.setText("");
    campoSenha.setText("");
    campoConfirmarSenha.setText("");
    labelStatusCliente.setText("");
  }

  /*
   * private boolean eInteiro(String str) {
   * try {
   * Integer.parseInt(str);
   * return true;
   * } catch (NumberFormatException e) {
   * return false;
   * }
   * }
   */
}
