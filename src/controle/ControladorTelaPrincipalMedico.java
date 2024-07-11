package controle;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import controle.controle_back.ConsultaController;
import controle.controle_back.MedicoVeterinarioController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import modelo.Consulta;

public class ControladorTelaPrincipalMedico extends ControladorBase implements Initializable {
  @FXML
  private AnchorPane AnchorPanePrincipalMedico;

  @FXML
  private Pane paneMenu, PaneConteudo, PaneMeusClientes, PaneConsultasAgendadas, PaneExamesAgendados;

  @FXML
  private Button btnMeusClientes, btnConsultasAgendadas, btnExamesAgendados, btnSair, btnAcessarCliente,
      btnCancelarConsulta, btnCancelarExame;

  // TABELA CLIENTES
  @FXML
  private TableView<?> tabelaClientes;

  @FXML
  private TableColumn<?, ?> colunaClienteNome;

  @FXML
  private TableColumn<?, ?> colunaClienteSobrenome;

  @FXML
  private TableColumn<?, ?> colunaClienteCPF;
  
  @FXML
  private TableColumn<?, ?> colunaClienteTelefone;

  // TABELA CONSULTAS
  @FXML
  private TableView<Consulta> tabelaConsultas;

  @FXML
  private TableColumn<Consulta, Date> colunaConsultaData;

  @FXML
  private TableColumn<Consulta, String> colunaConsultaHora;

  @FXML
  private TableColumn<Consulta, String> colunaConsultaNomeCliente;
  
  @FXML
  private TableColumn<Consulta, Long> colunaConsultaCPF;
  
  @FXML
  private TableColumn<Consulta,String> colunaConsultaNomePet;
  
// TABELA EXAMES
  @FXML
  private TableView<?> tabelaExames;

  @FXML
  private TableColumn<?, ?> colunaExameData;
  
  @FXML
  private TableColumn<?, ?> colunaExameHora;

  @FXML
  private TableColumn<?, ?> colunaExameTipo;
  
  
  @FXML
  private TableColumn<?, ?> colunaExameCPF;
  
  @FXML
  private TableColumn<?, ?> colunaExameNomePet;

  @FXML
  private Label labelNome;

  @FXML
  private ImageView fotoPerfil;

  private static ConsultaController cct = new ConsultaController();
  private static MedicoVeterinarioController mc = new MedicoVeterinarioController();
  
  private static ObservableList <Consulta> consultas = FXCollections.observableArrayList(cct.listarConsultasMedico(ControladorTelaLoginProfissionalMedico.getCrmv()));

  private Stage stage;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    visibilidadeTelas(true, false, false, false);
    labelNome.setText("Bem vindx Dr.!!!");
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
  void meusClientes(ActionEvent event) {
    visibilidadeTelas(false, true, false, false);
  }

  @FXML
  void acessarCliente(ActionEvent event) {

  }

  @FXML
  void consultasAgendadas(ActionEvent event) {
    colunaConsultaNomePet.setCellValueFactory(new PropertyValueFactory<Consulta,String>("nomePet"));
    colunaConsultaHora.setCellValueFactory(new PropertyValueFactory<Consulta,String>("horario"));
    colunaConsultaData.setCellValueFactory(new PropertyValueFactory<Consulta,Date>("date"));
    colunaConsultaCPF.setCellValueFactory(new PropertyValueFactory<Consulta,Long>("cpfDono"));
    tabelaConsultas.setItems(consultas);
    visibilidadeTelas(false, false, true, false);
  }

  @FXML
  void cancelarConsulta(ActionEvent event) {

  }

  @FXML
  void examesAgendados(ActionEvent event) {
    visibilidadeTelas(false, false, false, true);
  }

  @FXML
  void cancelarExame(ActionEvent event) {

  }

  public void visibilidadeTelas(boolean conteudo, boolean meusClientes, boolean consultasAgendadas,
      boolean examesAgendados) {
    PaneConteudo.setVisible(conteudo);
    PaneMeusClientes.setVisible(meusClientes);
    PaneConsultasAgendadas.setVisible(consultasAgendadas);
    PaneExamesAgendados.setVisible(examesAgendados);
  }

  @FXML
  void sair(ActionEvent event) {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaEscolhaProfissional.fxml");
  }

}
