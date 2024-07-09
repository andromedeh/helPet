package controle;
import modelo.*;
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

public class ControladorTelaLoginAdministrador extends ControladorBase implements Initializable {
    @FXML
    private AnchorPane AnchorPaneTelaLoginAdm;
    @FXML
    private TextField campoCodigo, campoSenhaTexto;
    @FXML
    private Label labelStatus;
    @FXML
    private PasswordField campoSenhaOculto;
    @FXML
    private Button btnVoltar, btnVerSenha, btnLoginAdm;
    @FXML
    private ImageView iconBtnVer;

    private boolean controleBtnVer = true;
    private Administrador Adm = new Administrador ();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      //Inicializa tudo limpo
      limparCampos();
      // Configuração inicial: PasswordField visível, TextField oculto
      campoSenhaTexto.setVisible(false);
      campoSenhaTexto.setManaged(false);
      // Sincronizar o conteúdo dos campos
      campoSenhaOculto.textProperty().bindBidirectional(campoSenhaTexto.textProperty());
    }

    @FXML
    void loginAdm(ActionEvent event) {
      int valorCodigo = 0;
      String valorSenha = campoSenhaTexto.getText();
      
      if (campoCodigo.getText().isEmpty() || valorSenha.isEmpty()) {
        labelStatus.setText("Preencha todos os campos!");
        return;
      }

      if (!eInteiro(campoCodigo.getText())) {
        labelStatus.setText("Entrada inválida para o código. Por favor, insira um número válido.");
        return;
      } else {
        valorCodigo = Integer.parseInt(campoCodigo.getText());
      }
      
      // verifica se os valores de codigo e senha sao do adm
      if (valorCodigo != Adm.getCodigo() || !valorSenha.equals(Adm.getSenha())){ 
        labelStatus.setText("Codigo ou senha incorretos.");
        return;
      } else { // loga como adm
        labelStatus.setText("");
        gerenciador.getStage().close();
        gerenciador.trocarCena("/visao/fxml/TelaPrincipalAdministrador.fxml");
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
      gerenciador.trocarCena("/visao/fxml/TelaInicial.fxml");
    }

    public void limparCampos (){
      campoCodigo.clear();
      campoSenhaTexto.clear();
      campoSenhaOculto.clear();
    }

    private boolean eInteiro(String str) {
      try {
        Integer.parseInt(str);
        return true;
      } catch (NumberFormatException e) {
          return false;
      }
    }

}
