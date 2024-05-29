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
import javafx.scene.layout.AnchorPane;

public class ControladorTelaLogin extends ControladorBase implements Initializable {

  @FXML
  private AnchorPane AnchorPaneTelaLogin;
  @FXML
  private Button btnLogin, btnVerSenha;
  @FXML
  private TextField campoEmailLogin;
  @FXML
  private PasswordField campoSenhaLogin;
  @FXML
  private Label labelStatus;

  private ControleCliente cliente = new ControleCliente();

  @Override
  public void initialize(URL url, ResourceBundle resources) {
    limparCampos();
    labelStatus.setVisible(false);

  }

  @FXML
  void login(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException {
    String email = campoEmailLogin.getText();
    String senha = campoSenhaLogin.getText();
    if (cliente.loginCliente(email, senha)) {
      gerenciador.getStage().close();
      gerenciador.trocarCena("/visao/fxml/TelaCliente.fxml");
    } else {
      labelStatus.setText("E-mail ou senha inválidos, tente novamente!");
      labelStatus.setVisible(true);
      campoEmailLogin.setPromptText("tente novamente");
      campoSenhaLogin.setPromptText("tente novamente");
      campoEmailLogin.clear();
      campoSenhaLogin.clear();
    }
  }

  void limparCampos() {
    campoEmailLogin.setText("");
    campoSenhaLogin.setText("");
  }

  @FXML
  void verSenha(ActionEvent event) {
    // logica para deixar o campo da senha visivel/invisivel ex: "123" ou "***"
  }
}
