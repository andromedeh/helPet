package controle;

import java.net.URL;
import java.util.ResourceBundle;

import controle.controle_back.MedicoVeterinarioController;
import controle.controle_back.ProfissionalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.MedicoVeterinario;
import modelo.Profissional;

public class ControladorTelaLoginProfissionalSecretaria extends ControladorBase implements Initializable {
  @FXML
  private TextField campoCPF, campoSenhaTexto;
  @FXML
  private Label labelStatus;
  @FXML
  private PasswordField campoSenhaOculto;
  @FXML
  private Button btnVoltar, btnVerSenha, btnLoginSecretaria;
  @FXML
  private ImageView iconBtnVer;
  private boolean controleBtnVer = true;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    limparCampos();
    // Configuração inicial: PasswordField visível, TextField oculto
    campoSenhaTexto.setVisible(false);
    campoSenhaTexto.setManaged(false);
    // Sincronizar o conteúdo dos campos
    campoSenhaOculto.textProperty().bindBidirectional(campoSenhaTexto.textProperty());
  }

  @FXML
  void loginSecretaria(ActionEvent event) {
    if (campoCPF.getText().isEmpty() || campoSenhaTexto.getText().isEmpty()) {
      labelStatus.setText("Preencha todos os campos!");
      return;
    }
    ProfissionalController p = new ProfissionalController();
    Profissional profissional = p.pesquisarProfissionals(Long.parseLong(campoCPF.getText()));
    if (profissional != null) {
      if (profissional.getSenha().equals(campoSenhaTexto.getText())
          && profissional.getFuncao().equals("SECRETÁRIO(A)")) {
        gerenciador.getStage().close();
        gerenciador.trocarCena("/visao/fxml/TelaPrincipalSecretaria.fxml");
      } else {
        labelStatus.setText("Senha inválida!");
      }
    } else {
      labelStatus.setText("CPF inválido!");
    }
  }

  @FXML
  void verSenha(ActionEvent event) {
    Image icon;
    if (controleBtnVer) {
      // Mostrar o TextField, ocultar o PasswordField
      campoSenhaOculto.setVisible(false);
      campoSenhaOculto.setManaged(false);
      campoSenhaTexto.setVisible(true);
      campoSenhaTexto.setManaged(true);
      icon = new Image("visao/img/iconOlhoFechado.png");
      controleBtnVer = false;
    } else {
      // Ocultar o TextField, mostrar o PasswordField
      campoSenhaOculto.setVisible(true);
      campoSenhaOculto.setManaged(true);
      campoSenhaTexto.setVisible(false);
      campoSenhaTexto.setManaged(false);
      icon = new Image("visao/img/iconOlhoAberto.png");
      controleBtnVer = true;
    }
    iconBtnVer.setImage(icon);
  }

  @FXML
  void voltar(ActionEvent event) {
    limparCampos();
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaEscolhaProfissional.fxml");
  }

  public void limparCampos() {
    campoCPF.clear();
    campoSenhaTexto.clear();
    campoSenhaOculto.clear();
  }
}
