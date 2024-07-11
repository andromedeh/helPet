package controle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
  private TableView<?> tabelaConsultas;

  @FXML
  private TableColumn<?, ?> colunaConsultaData;

  @FXML
  private TableColumn<?, ?> colunaConsultaHora;
  
  @FXML
  private TableColumn<?, ?> colunaConsultaMedico;

  @FXML
  private TableColumn<?, ?> colunaConsultaMedicoCpf;
  
  @FXML
  private TableColumn<?, ?> colunaConsultaCliente;
  
  @FXML
  private TableColumn<?, ?> colunaConsultaClienteCpf;
  
  @FXML
  private TableColumn<?, ?> colunaConsultaPet;

  private Stage stage;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
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

  }

  @FXML
  void cancelarConsulta(ActionEvent event) {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaEscolhaProfissional.fxml");
  }

  public void visibilidadeTelas(boolean conteudo, boolean administarConsultas) {
    PaneConteudo.setVisible(conteudo);
    PaneConsultas.setVisible(administarConsultas);
  }

  @FXML
  void sair(ActionEvent event) {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaCadastroConsulta.fxml");
  }
}
