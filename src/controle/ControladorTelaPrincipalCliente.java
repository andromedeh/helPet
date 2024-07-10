package controle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controle.controle_back.PetController;
import controle.controle_back.VacinaController;
import controle.controle_back.RemedioController;
import controle.controle_back.ExameController;
import controle.controle_back.ProfissionalController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
import modelo.Cliente;
import modelo.Exame;
import modelo.Pet;
import modelo.Profissional;
import modelo.Remedio;
import modelo.Vacina;

public class ControladorTelaPrincipalCliente extends ControladorBase implements Initializable {
  @FXML
  private AnchorPane AnchorPanePrincipalCliente;

  @FXML
  private Pane paneMenu, PaneConteudo, PaneMeusPets, PaneConsultas, PaneVacinacao, PaneRemedios, PaneExames,
      PaneNotificacao, PaneFAQ;

  // BOTOES DA PANE MENU
  @FXML
  private Button btnMeusPets, btnConsultas, btnVacinacao, btnRemedios, btnExames, btnNotificacoes,
      btnFAQ, btnSair;

  // BOTOES GERAIS
  @FXML
  private Button btnCadastrarPet, btnDeletarPet, btnAtualizarTabelaPet, btnSolicitarConsulta, btnCancelarConsulta,
      btnAtualizarTabelaConsulta, btnAtualizarTabelaVacinas, btnAtualizarTabelaRemedios, btnAtualizarTabelaExames;

  @FXML
  private Label labelNome;

  @FXML
  private ImageView fotoPerfil;

  // TABELA PETS
  @FXML
  private TableView<?> tabelaMeusPets;

  @FXML
  private TableColumn<?, ?> colunaMeuPetNome;
  
  @FXML
  private TableColumn<?, ?> colunaMeuPetEspecie;

  @FXML
  private TableColumn<?, ?> colunaMeuPetRaca;

  @FXML
  private TableColumn<?, ?> colunaMeuPetPeso;

  @FXML
  private TableColumn<?, ?> colunaMeuPetIdade;

  // TABELA CONSULTAS
  @FXML
  private TableView<?> tabelaConsultas;

  @FXML
  private TableColumn<?, ?> colunaConsultaData;

  @FXML
  private TableColumn<?, ?> colunaConsultaHora;

  @FXML
  private TableColumn<?, ?> colunaConsultaMedico;

  @FXML
  private TableColumn<?, ?> colunaConsultaPet;
  
  // TABELA VACINAS
  @FXML
  private TableView<?> tabelaVacinacao;

  @FXML
  private TableColumn<?, ?> colunaVacinacaoCodigo;

  @FXML
  private TableColumn<?, ?> colunaVacinacaoNome;

  @FXML
  private TableColumn<?, ?> colunaVacinacaoDataAplicacao;
  
  @FXML
  private TableColumn<?, ?> colunaVacinacaoDataReforco;
  
  @FXML
  private TableColumn<?, ?> colunaVacinacaoMedico;
  
  @FXML
  private TableColumn<?, ?> colunaVacinacaoPet;
  
  // TABELA REMEDIOS
  @FXML
  private TableView<?> tabelaRemedios;

  @FXML
  private TableColumn<?, ?> colunaRemedioCodigo;

  @FXML
  private TableColumn<?, ?> colunaRemedioNome;
  
  @FXML
  private TableColumn<?, ?> colunaRemedioQtd;
  
  @FXML
  private TableColumn<?, ?> colunaRemedioHorario;
  
  @FXML
  private TableColumn<?, ?> colunaRemedioDuracao;
  
  @FXML
  private TableColumn<?, ?> colunaRemedioMedico;
  
  @FXML
  private TableColumn<?, ?> colunaRemedioPet;

  // TABELA EXAMES
  @FXML
  private TableView<?> tabelaExames;

  @FXML
  private TableColumn<?, ?> colunaExameData;
  
  @FXML
  private TableColumn<?, ?> colunaExameHora;
  
  @FXML
  private TableColumn<?, ?> colunaExameCodigo;
  
  @FXML
  private TableColumn<?, ?> colunaExameNome;
  
  @FXML
  private TableColumn<?, ?> colunaExameMedico;
  
  @FXML
  private TableColumn<?, ?> colunaExamePet;
  
  private static PetController ptc = new PetController();
  private static VacinaController vc = new VacinaController();
  private static RemedioController rc = new RemedioController();
  private static ExameController ec = new ExameController();

  private static ObservableList <Pet> pets = FXCollections.observableArrayList(ptc.listarPet());
  private static ObservableList <Vacina> vacinas = FXCollections.observableArrayList(vc.listarVacinas());
  private static ObservableList <Remedio> remedios = FXCollections.observableArrayList(rc.listarRemedios());
  private static ObservableList <Exame> exames = FXCollections.observableArrayList(ec.listarExames());

  private Stage stage;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    visibilidadeTelas(true, false, false, false, false, false, false, false);
    labelNome.setText("Bem vindx, Fulano!");
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
  void meusPets(ActionEvent event) {
    visibilidadeTelas(false, true, false, false, false, false, false, false);
  }

  @FXML
  void cadastrarPet(ActionEvent event) {
    AnchorPane anchorPane;
    try {
      anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/fxml/TelaCadastroPet.fxml"));
      Stage secondStage = new Stage();
      Scene secondScene = new Scene(anchorPane);
      secondStage.setScene(secondScene);
      secondStage.show();
    } catch (IOException ex) {
    }
  }

  @FXML
  void deletarPet(ActionEvent event) {

  }

  @FXML
  void atualizarTabelaPet(ActionEvent event) {

  }

  @FXML
  void Consultas(ActionEvent event) {
    visibilidadeTelas(false, false, true, false, false, false, false, false);
  }

  @FXML
  void solicitarConsulta(ActionEvent event) {

  }

  @FXML
  void cancelarConsulta(ActionEvent event) {

  }

  @FXML
  void atualizarTabelaConsulta(ActionEvent event) {

  }

  @FXML
  void vacinacao(ActionEvent event) {
    visibilidadeTelas(false, false, false, true, false, false, false, false);
  }

  @FXML
  void atualizarTabelaVacinas(ActionEvent event) {

  }

  @FXML
  void remedios(ActionEvent event) {
    visibilidadeTelas(false, false, false, false, true, false, false, false);
  }

  @FXML
  void atualizarTabelaRemedios(ActionEvent event) {

  }

  @FXML
  void exames(ActionEvent event) {
    visibilidadeTelas(false, false, false, false, false, true, false, false);
  }

  @FXML
  void atualizarTabelaExames(ActionEvent event) {

  }

  @FXML
  void notificacao(ActionEvent event) {
    visibilidadeTelas(false, false, false, false, false, false, true, false);
  }

  @FXML
  void faq(ActionEvent event) {
    visibilidadeTelas(false, false, false, false, false, false, false, true);
  }

  public void visibilidadeTelas(boolean conteudo, boolean meusPets, boolean solicitarConsulta, boolean vacinacao,
      boolean remedios, boolean exames, boolean notificacao, boolean faq) {
    PaneConteudo.setVisible(conteudo);
    PaneMeusPets.setVisible(meusPets);
    PaneConsultas.setVisible(solicitarConsulta);
    PaneVacinacao.setVisible(vacinacao);
    PaneRemedios.setVisible(remedios);
    PaneExames.setVisible(exames);
    PaneNotificacao.setVisible(notificacao);
    PaneFAQ.setVisible(faq);
  }

  @FXML
  void sair(ActionEvent event) {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaEscolhaCliente.fxml");
  }
}
