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

public class ControladorTelaCadastroClientePet extends ControladorBase implements Initializable {
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
  private AnchorPane AnchorPaneCadastroClientePet, AnchorPaneTelaCadastroPet, AnchorPaneTelaCadastroCliente,
      AnchorPaneConfirmacao;
  // informacoes/atributos do cliente
  @FXML
  private TextField campoNome, campoSobrenome, campoCpf, campoTelefone, campoEmailCadastro, campoEndereco;

  @FXML
  private PasswordField campoSenha, campoConfirmarSenha;

  // informacoes/atributos do pet
  @FXML
  private TextField campoNomePet, campoRaca, campoIdade, campoPeso;

  @FXML
  private RadioButton rbGato, rbCachorro, rbSim, rbNao;

  @FXML
  private ToggleGroup especie, maisPet;

  // botoes e labels
  @FXML
  private Button btnContinuarPet, btnContinuar, btnConfirmar, bntVoltar;
  @FXML
  private Label labelStatusCliente, labelStatusPet;

  String especiePet;
  String maisPetValor;

  // DADOS CLIENTE
  String nome;
  String sobrenome;
  long cpf;
  long telefone;
  String email;
  String endereco;
  String senha;
  String confirmarSenha;
  // DADOS PET
  String nomePet;
  String raca;
  float idade;
  float peso;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    limparCamposCliente();
    limparCamposPet();
    btnConfirmar.setVisible(true);
    AnchorPaneTelaCadastroCliente.setVisible(true);
    AnchorPaneTelaCadastroPet.setVisible(false);
    AnchorPaneConfirmacao.setVisible(false);
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

    AnchorPaneTelaCadastroCliente.setVisible(false);
    AnchorPaneTelaCadastroPet.setVisible(true);
    AnchorPaneConfirmacao.setVisible(false);
  }

  // confirma cadastro do pet e mostra tela de confirmação
  @FXML
  void confirmar(ActionEvent event) {
    nomePet = campoNomePet.getText();
    raca = campoRaca.getText();
    idade = 0;
    peso = 0;

    if (nomePet.isEmpty() || raca.isEmpty() || campoIdade.getText().isEmpty() || campoPeso.getText().isEmpty()
        || especie.getSelectedToggle() == null || maisPet.getSelectedToggle() == null) {
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
    RadioButton maisPetSelecionada = (RadioButton) maisPet.getSelectedToggle();
    maisPetValor = maisPetSelecionada.getText(); // "Sim" ou "Não"

    // IMPLEMENTAR LOGICA PARA SALVAR OS DADOS DO CLIENTE E DO PET NO BANCO DE DADOS

    btnConfirmar.setVisible(false);

    limparCamposPet();

    AnchorPaneTelaCadastroCliente.setVisible(false);
    AnchorPaneTelaCadastroPet.setVisible(true);
    AnchorPaneConfirmacao.setVisible(true);
  }

  // verifica se o cliente quer cadastrar mais um pet ou nao
  @FXML
  void continuarCadastroPet(ActionEvent event) {
    if (maisPetValor.equals("SIM")) {
      AnchorPaneConfirmacao.setVisible(false);
    } else {
      limparCamposCliente();
      gerenciador.getStage().close();
      gerenciador.trocarCena("/visao/fxml/TelaEscolhaCliente.fxml");
    }
    btnConfirmar.setVisible(true);
    labelStatusPet.setText("");
  }

  @FXML
  void voltar(ActionEvent event) {
    limparCamposCliente();
    limparCamposPet();
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

  private boolean eFlutuante(String str) {
    try {
      Float.parseFloat(str);
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

  void limparCamposPet() {
    campoNomePet.setText("");
    campoRaca.setText("");
    campoIdade.setText("");
    campoPeso.setText("");
    especie.selectToggle(null);
    maisPet.selectToggle(null);
    labelStatusCliente.setText("");
  }
}
