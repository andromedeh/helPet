package controle;

import java.net.URL;
import java.util.ResourceBundle;

import controle.controle_back.MedicoVeterinarioController;
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

public class ControladorTelaLoginProfissionalMedico extends ControladorBase implements Initializable {
  @FXML
  private TextField campoCRMV, campoSenhaTexto;
  @FXML
  private Label labelStatus;
  @FXML
  private PasswordField campoSenhaOculto;
  @FXML
  private Button btnVoltar, btnVerSenha, btnLoginMedico;
  @FXML
  private ImageView iconBtnVer;
  private boolean controleBtnVer = true;
  private static int crmv;

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
  void loginMedico(ActionEvent event) {// email = crmv
    if (campoCRMV.getText().isEmpty() || campoSenhaTexto.getText().isEmpty()) {
      labelStatus.setText("Preencha todos os campos!");
      return;
    }
    MedicoVeterinarioController m = new MedicoVeterinarioController();
    MedicoVeterinario medico = m.pesquisarMedicoVeterinario(Integer.parseInt(campoCRMV.getText()));
    if (medico != null) {
      if (medico.getCpf() == (Long.parseLong(campoSenhaTexto.getText()))) {
        gerenciador.getStage().close();
        this.crmv = Integer.parseInt(campoCRMV.getText());
        gerenciador.getStage().close();
        gerenciador.trocarCena("/visao/fxml/TelaPrincipalMedico.fxml");
      } else {
        labelStatus.setText("Senha inválida!");
      }
    } else {
      labelStatus.setText("CRMV inválido!");
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
    campoCRMV.clear();
    campoSenhaTexto.clear();
    campoSenhaOculto.clear();
  }

  public static int getCrmv() {
    return crmv;
  }

}
