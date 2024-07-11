package controle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import controle.controle_back.PetController;
import controle.controle_back.RemedioController;
import controle.controle_back.AplicaVacinaController;
import controle.controle_back.ClienteController;
import controle.controle_back.ConsultaController;
import controle.controle_back.ExameController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import modelo.AplicaVacina;
import modelo.Consulta;
import modelo.Exame;
import modelo.Pet;
import modelo.PrescricaoRemedio;
import modelo.Remedio;

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
  private TableView<Pet> tabelaMeusPets;

  @FXML
  private TableColumn<Pet, String> colunaMeuPetNome;
  
  @FXML
  private TableColumn<Pet, String> colunaMeuPetEspecie;

  @FXML
  private TableColumn<Pet, String> colunaMeuPetRaca;

  @FXML
  private TableColumn<Pet, Float> colunaMeuPetPeso;

  @FXML
  private TableColumn<Pet, Integer> colunaMeuPetIdade;

  // TABELA CONSULTAS
  @FXML
  private TableView<Consulta> tabelaConsultas;

  @FXML
  private TableColumn<Consulta, Date> colunaConsultaData;

  @FXML
  private TableColumn<Consulta, String> colunaConsultaHora;

  @FXML
  private TableColumn<Consulta, Integer> colunaConsultaMedico;

  @FXML
  private TableColumn<Consulta, String> colunaConsultaPet;
  
  // TABELA VACINAS
  @FXML
  private TableView<AplicaVacina> tabelaVacinacao;

  @FXML
  private TableColumn<AplicaVacina, Integer> colunaVacinacaoCodigo;

  @FXML
  private TableColumn<AplicaVacina, String> colunaVacinacaoNome;

  @FXML
  private TableColumn<AplicaVacina, Date> colunaVacinacaoDataAplicacao;
  
  @FXML
  private TableColumn<AplicaVacina, String> colunaVacinacaoDataReforco;
  
  @FXML
  private TableColumn<AplicaVacina, Integer> colunaVacinacaoMedico;
  
  @FXML
  private TableColumn<AplicaVacina, String> colunaVacinacaoPet;
  
  // TABELA REMEDIOS
  @FXML
  private TableView<PrescricaoRemedio> tabelaRemedios;

  @FXML
  private TableColumn<PrescricaoRemedio, Integer> colunaRemedioCodigo;

  @FXML
  private TableColumn<PrescricaoRemedio, String> colunaRemedioNome;
  
  @FXML
  private TableColumn<PrescricaoRemedio, Integer> colunaRemedioQtd;
  
  @FXML
  private TableColumn<PrescricaoRemedio, String> colunaRemedioHorario;
  
  @FXML
  private TableColumn<PrescricaoRemedio, Integer> colunaRemedioDuracao;
  
  @FXML
  private TableColumn<PrescricaoRemedio, Integer> colunaRemedioMedico;
  
  @FXML
  private TableColumn<PrescricaoRemedio, String> colunaRemedioPet;

  // TABELA EXAMES
  @FXML
  private TableView<Exame> tabelaExames;

  @FXML
  private TableColumn<Exame, Date> colunaExameData;
  
  @FXML
  private TableColumn<Exame, String> colunaExameHora;
  
  @FXML
  private TableColumn<Exame, Integer> colunaExameCodigo;
  
  @FXML
  private TableColumn<Exame, String> colunaExameNome;
  
  @FXML
  private TableColumn<Exame, Integer> colunaExameMedico;
  
  @FXML
  private TableColumn<Exame, String> colunaExamePet;
  
  private static PetController ptc = new PetController();
  private static AplicaVacinaController avc = new AplicaVacinaController();
  private static RemedioController rc = new RemedioController();
  private static ExameController ec = new ExameController();
  private static ClienteController cc = new ClienteController();
  private static ConsultaController cct = new ConsultaController();
  
  private static ObservableList <Consulta> consultas = FXCollections.observableArrayList(cct.listarConsultasCliente(ControladorTelaLoginCliente.getCpf()));
  private static ObservableList <Pet> pets = FXCollections.observableArrayList(ptc.listarPetDono(ControladorTelaLoginCliente.getCpf()));
  private static ObservableList <AplicaVacina> vacinas = FXCollections.observableArrayList(avc.listarAplicaVacinasCliente(ControladorTelaLoginCliente.getCpf()));
  private static ObservableList <Remedio> remedios = FXCollections.observableArrayList(rc.listarRemedios());
  private static ObservableList <Exame> exames = FXCollections.observableArrayList(ec.listarExames());

  private Stage stage;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
    visibilidadeTelas(true, false, false, false, false, false, false, false);
    labelNome.setText("Bem vindx, "+cc.pesquisarCliente(ControladorTelaLoginCliente.getCpf()).getNome());
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
    atualizarTabelaPet(event);
    colunaMeuPetNome.setCellValueFactory(new PropertyValueFactory<Pet,String>("nomePet"));
    colunaMeuPetEspecie.setCellValueFactory(new PropertyValueFactory<Pet,String>("especie"));
    colunaMeuPetIdade.setCellValueFactory(new PropertyValueFactory<Pet,Integer>("idade"));
    colunaMeuPetPeso.setCellValueFactory(new PropertyValueFactory<Pet,Float>("peso"));
    colunaMeuPetRaca.setCellValueFactory(new PropertyValueFactory<Pet,String>("raca"));
    tabelaMeusPets.setItems(pets);
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
    Pet selecionado = tabelaMeusPets.getSelectionModel().getSelectedItem();
    if (selecionado != null) {
      String nomePet = selecionado.getNomePet();
      String raca = selecionado.getRaca();
      int idade = selecionado.getIdade();
      float peso = selecionado.getPeso();
      String especie = selecionado.getEspecie();
      Long cpfDono = selecionado.getCpfDono();
      ptc.deletarPet(nomePet, raca, idade, peso, especie,cpfDono.longValue());
      atualizarTabelaPet(event);
    }
  }

  @FXML
  void atualizarTabelaPet(ActionEvent event) {
    pets = FXCollections.observableArrayList(ptc.listarPetDono(ControladorTelaLoginCliente.getCpf()));
    tabelaMeusPets.setItems(pets);
  }

  @FXML
  void Consultas(ActionEvent event) {
    colunaConsultaPet.setCellValueFactory(new PropertyValueFactory<Consulta,String>("nomePet"));
    colunaConsultaHora.setCellValueFactory(new PropertyValueFactory<Consulta,String>("horario"));
    colunaConsultaData.setCellValueFactory(new PropertyValueFactory<Consulta,Date>("date"));
    colunaConsultaMedico.setCellValueFactory(new PropertyValueFactory<Consulta,Integer>("crmvMedico"));
    tabelaConsultas.setItems(consultas);
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
    atualizarTabelaVacinas(event);
    colunaVacinacaoCodigo.setCellValueFactory(new PropertyValueFactory<AplicaVacina,Integer>("codigoVacina"));
    colunaVacinacaoDataAplicacao.setCellValueFactory(new PropertyValueFactory<AplicaVacina,Date>("data_aplicacao"));
    colunaVacinacaoDataReforco.setCellValueFactory(new PropertyValueFactory<AplicaVacina,String>("reforcoV"));
    colunaVacinacaoMedico.setCellValueFactory(new PropertyValueFactory<AplicaVacina,Integer>("crmvMedico"));
    colunaVacinacaoNome.setCellValueFactory(new PropertyValueFactory<AplicaVacina,String>("nomeV"));
    colunaVacinacaoPet.setCellValueFactory(new PropertyValueFactory<AplicaVacina,String>("nomePet"));
    tabelaVacinacao.setItems(vacinas);
    visibilidadeTelas(false, false, false, true, false, false, false, false);
  }

  @FXML
  void atualizarTabelaVacinas(ActionEvent event) {
    vacinas = FXCollections.observableArrayList(avc.listarAplicaVacinasCliente(ControladorTelaLoginCliente.getCpf()));
    tabelaVacinacao.setItems(vacinas);
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
