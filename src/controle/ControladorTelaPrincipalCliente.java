package controle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ControladorTelaPrincipalCliente extends ControladorBase implements Initializable {
  @FXML
  private AnchorPane AnchorPanePrincipalCliente, AnchorPaneConfirmacaoCadastroPet;

  @FXML
  private Pane paneMenu, PaneConteudo, PaneCadastrarPet, PaneConsultas, PaneVacinacao, PaneRemedios, PaneExames,
      PaneNotificacao, PaneFAQ;

  @FXML
  private Button btnCadastrarNovoPet, btnConsultas, btnVacinacao, btnRemedios, btnExames, btnNotificacoes,
      btnFAQ, btnSair, btnSolicitarConsulta, btnCancelarConsulta;

  @FXML
  private Label labelNome;

  @FXML
  private ImageView fotoPerfil;

  @FXML
  private TableView<?> tabelaConsultas;

  @FXML
  private TableColumn<?, ?> colunaConsultaData, colunaConsultaHora, colunaConsultaMedico, colunaConsultaPet;

  @FXML
  private TableView<?> tabelaVacinacao;

  @FXML
  private TableColumn<?, ?> colunaVacinacaoCodigo, colunaVacinacaoNome, colunaVacinacaoDataAplicacao,
      colunaVacinacaoDataReforço, colunaVacinacaoMedico, colunaVacinacaoPet;

  @FXML
  private TableView<?> tabelaRemedios;

  @FXML
  private TableColumn<?, ?> colunaRemedioCodigo, colunaRemedioNome, colunaRemedioQtd, colunaRemedioHorario,
      colunaRemedioDuracao, colunaRemedioMedico, colunaRemedioPet;

  @FXML
  private TableView<?> tabelaExames;

  @FXML
  private TableColumn<?, ?> colunaExameData, colunaExameHora, colunaExameCodigo, colunaExameNome, colunaExameMedico,
      colunaExamePet;

  private Stage stage;

  // Tela CadastrarNovoPet
  @FXML
  private TextField campoNomePet, campoRaca, campoIdade, campoPeso;
  @FXML
  private RadioButton rbGato, rbCachorro;
  @FXML
  private ToggleGroup especie;
  @FXML
  private Button btnConfirmarCadastroPet, btnContinuarPet;
  @FXML
  private Label labelStatusPet;
  String especiePet;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    visibilidadeTelas(true, false, false, false, false, false, false, false);
    labelNome.setText("Bem vindx, Fulano!");
    AnchorPaneConfirmacaoCadastroPet.setVisible(false);
    limparCamposPet();
  }

  @FXML
  void selecionarFotoPerfil(MouseEvent event) {
    FileChooser selecionarFoto = new FileChooser();
    selecionarFoto.getExtensionFilters().add(new ExtensionFilter("Foto", "*.jpg", "*.jpeg", "*.png"));

    File fotoSelecionada = selecionarFoto.showOpenDialog(stage);
    if (fotoSelecionada != null) {
      String caminhoImagem = fotoSelecionada.toURI().toString();
      Image fotoImage = new Image(caminhoImagem);
      fotoPerfil.setImage(fotoImage);
    }
  }

  @FXML
  void cadastrarPet(ActionEvent event) {
    visibilidadeTelas(false, true, false, false, false, false, false, false);
  }

  @FXML
  void Consultas(ActionEvent event) {
    visibilidadeTelas(false, false, true, false, false, false, false, false);
    limparCamposPet();
  }

  @FXML
  void solicitarConsulta(ActionEvent event) {

  }

  @FXML
  void cancelarConsulta(ActionEvent event) {

  }

  @FXML
  void vacinacao(ActionEvent event) {
    visibilidadeTelas(false, false, false, true, false, false, false, false);
    limparCamposPet();
  }

  @FXML
  void remedios(ActionEvent event) {
    visibilidadeTelas(false, false, false, false, true, false, false, false);
    limparCamposPet();
  }

  @FXML
  void exames(ActionEvent event) {
    visibilidadeTelas(false, false, false, false, false, true, false, false);
    limparCamposPet();
  }

  @FXML
  void notificacao(ActionEvent event) {
    visibilidadeTelas(false, false, false, false, false, false, true, false);
    limparCamposPet();
  }

  @FXML
  void faq(ActionEvent event) {
    visibilidadeTelas(false, false, false, false, false, false, false, true);
    limparCamposPet();
  }

  // =========CADASTRO DO PET=========
  @FXML
  void cadastroNovoPet(ActionEvent event) {
    String nomePet = campoNomePet.getText();
    String raca = campoRaca.getText();
    float idade = 0;
    float peso = 0;

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

    // LOGICA DE BD

    limparCamposPet();

    AnchorPaneConfirmacaoCadastroPet.setVisible(true);

  }

  @FXML
  void continuarCadastroPet(ActionEvent event) {
    AnchorPaneConfirmacaoCadastroPet.setVisible(false);
    visibilidadeTelas(true, false, false, false, false, false, false, false);
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
    labelStatusPet.setText("");
  }
  // =========CADASTRO DO PET=========

  public void visibilidadeTelas(boolean conteudo, boolean cadastroPet, boolean solicitarConsulta, boolean vacinacao,
      boolean remedios, boolean exames, boolean notificacao, boolean faq) {
    PaneConteudo.setVisible(conteudo);
    PaneCadastrarPet.setVisible(cadastroPet);
    PaneConsultas.setVisible(solicitarConsulta);
    PaneVacinacao.setVisible(vacinacao);
    PaneRemedios.setVisible(remedios);
    PaneExames.setVisible(exames);
    PaneNotificacao.setVisible(notificacao);
    PaneFAQ.setVisible(faq);
  }

  @FXML
  void sair(ActionEvent event) {
    limparCamposPet();
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaEscolhaCliente.fxml");
  }
}
