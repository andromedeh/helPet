package controle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import modelo.Administrador;

public class ControladorTelaLogin extends ControladorBase implements Initializable {

  @FXML
  private AnchorPane AnchorPaneTelaLoginCliente, AnchorPaneTelaLoginFuncionario, AnchorPaneTelaLoginAdm;
  @FXML
  private Button btnLoginCliente, btnLoginFuncionario, btnLoginAdm, btnVerSenha, btnVoltar;
  @FXML
  private TextField campoEmailLoginCliente, campoEmailLoginFuncionario, campoEmailLoginAdm;
  @FXML
  private PasswordField campoSenhaLoginCliente, campoSenhaLoginFuncionario, campoSenhaLoginAdm;
  @FXML
  private Label labelStatus;
  @FXML
  private ImageView iconBtnVer;
  private ControleCliente cliente = new ControleCliente();
  private Administrador adm = new Administrador();
  private boolean controleBtnVer = true;

  @Override
  public void initialize(URL url, ResourceBundle resources) {
    limparCampos();
    labelStatus.setText("");
    controleBtnVer = true;
  }

  @FXML
  void loginCliente(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException {
    String email = campoEmailLoginCliente.getText();
    String senha = campoSenhaLoginCliente.getText();
    if (cliente.loginCliente(email, senha)) {
      gerenciador.getStage().close();
      gerenciador.trocarCena("/visao/fxml/TelaCliente.fxml");
    } else {
      labelStatus.setText("E-mail ou senha inválidos, tente novamente!");
      campoEmailLoginCliente.setPromptText("tente novamente");
      campoSenhaLoginCliente.setPromptText("tente novamente");
      campoEmailLoginCliente.clear();
      campoSenhaLoginCliente.clear();
    }
  }

  @FXML
  void loginFuncionario(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException {

  }

  @FXML
  void loginAdm(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException {
    String email = campoEmailLoginAdm.getText();
    String senha = campoSenhaLoginAdm.getText();
    if (email.contentEquals(adm.getEmail()) && senha.contentEquals(adm.getSenha())) {
      gerenciador.getStage().close();
      gerenciador.trocarCena("/visao/fxml/TelaCliente.fxml");
    } else {
      labelStatus.setText("Administrador inválido, tente novamente!");
      campoEmailLoginAdm.setPromptText("tente novamente");
      campoSenhaLoginAdm.setPromptText("tente novamente");
      campoEmailLoginAdm.clear();
      campoSenhaLoginAdm.clear();
    }

  }

  void limparCampos() {
    if (campoEmailLoginCliente != null && campoSenhaLoginCliente != null) {
      campoEmailLoginCliente.clear();
      campoSenhaLoginCliente.clear();
    }
    if (campoEmailLoginFuncionario != null && campoSenhaLoginFuncionario != null) {
      campoEmailLoginFuncionario.clear();
      campoSenhaLoginFuncionario.clear();
    }
    if (campoEmailLoginAdm != null && campoSenhaLoginAdm != null) {
      campoEmailLoginAdm.clear();
      campoSenhaLoginAdm.clear();
    }
  }

  @FXML
  void verSenha(ActionEvent event) {
    // logica para deixar o campo da senha visivel/invisivel ex: "123" ou "***"
    Image icon;
    if (controleBtnVer){ // se estiver aberto e quiser fechar
      icon = new Image("visao/img/iconOlhoFechado.png");
      controleBtnVer = false;
    } else{ // esta fechado
      icon = new Image("visao/img/iconOlhoAberto.png");
      controleBtnVer = true;
    } // fim do if-else
    iconBtnVer.setImage(icon);
  }

  @FXML
  void voltar(ActionEvent event) {
    limparCampos();
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaEscolhaPerfil.fxml");
  }
}
