package controle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import controle.controle_back.ConsultaController;
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
import modelo.Consulta;
import javafx.stage.Stage;

public class ControladorTelaPrincipalSecretaria extends ControladorBase implements Initializable {
  @FXML
  private AnchorPane AnchorPanePrincipalSecretaria;

  @FXML
  private Pane paneMenu, PaneConteudo, PaneConsultas;

  @FXML
  private Button btnAdministarConsultas, btnSair, btnMarcarConsulta, btnCancelarConsulta;

  @FXML
  private Label labelNome;

  @FXML
  private ImageView fotoPerfil;

  @FXML
  private TableView<Consulta> tabelaConsultas;

  @FXML
  private TableColumn<Consulta, Date> colunaConsultaData;

  @FXML
  private TableColumn<Consulta, String> colunaConsultaHora;
  

  @FXML
  private TableColumn<Consulta, Integer> colunaConsultaMedicoCpf;
  
  @FXML
  private TableColumn<Consulta, Long> colunaConsultaClienteCpf;
  
  @FXML
  private TableColumn<Consulta, String> colunaConsultaPet;

  private Stage stage;
  private static ConsultaController cc = new ConsultaController();
  private static ObservableList <Consulta> consultas = FXCollections.observableArrayList(cc.listarConsultas());

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    colunaConsultaPet.setCellValueFactory(new PropertyValueFactory<Consulta,String>("nomePet"));
    colunaConsultaClienteCpf.setCellValueFactory(new PropertyValueFactory<Consulta,Long>("cpfDono"));
    colunaConsultaHora.setCellValueFactory(new PropertyValueFactory<Consulta,String>("horario"));
    colunaConsultaData.setCellValueFactory(new PropertyValueFactory<Consulta,Date>("date"));
    colunaConsultaMedicoCpf.setCellValueFactory(new PropertyValueFactory<Consulta,Integer>("crmvMedico"));
    tabelaConsultas.setItems(consultas);
    visibilidadeTelas(true, false);
    labelNome.setText("Bem vindx, Secre. Fulano!");
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
  void administrarConsultas(ActionEvent event) {
    visibilidadeTelas(false, true);
  }

  @FXML
  void marcarConsulta(ActionEvent event) {
      AnchorPane anchorPane;
    try {
      anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/fxml/TelaCadastroConsulta.fxml"));
      Stage secondStage = new Stage();
      Scene secondScene = new Scene(anchorPane);
      secondStage.setScene(secondScene);
      secondStage.show();
    } catch (IOException ex) {
    }
    consultas = FXCollections.observableArrayList(cc.listarConsultas());
    tabelaConsultas.setItems(consultas);
  }

  @FXML
  void cancelarConsulta(ActionEvent event) {
    Consulta selecionado = tabelaConsultas.getSelectionModel().getSelectedItem();
    if (selecionado != null) {
      String nome = selecionado.getHorario();
      Long cpfDono = selecionado.getCpfDono();
      int crmv = selecionado.getCrmvMedico();
      Date date = selecionado.getDate();
      String horario = selecionado.getHorario();
      cc.deletarConsulta(nome, cpfDono, crmv, horario, date);
      
      consultas = FXCollections.observableArrayList(cc.listarConsultas());
      tabelaConsultas.setItems(consultas);
      
    }
  }

  public void visibilidadeTelas(boolean conteudo, boolean administarConsultas) {
    PaneConteudo.setVisible(conteudo);
    PaneConsultas.setVisible(administarConsultas);
  }

  @FXML
  void sair(ActionEvent event) {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaEscolhaProfissional.fxml");
  }
}
