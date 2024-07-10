package controle;

import java.net.URL;
import java.util.ResourceBundle;

import controle.controle_back.ClienteController;
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
import modelo.Cliente;

public class ControladorTelaLoginCliente extends ControladorBase implements Initializable {
  @FXML
  private AnchorPane AnchorPaneTelaLoginCliente;
  @FXML
  private TextField campoEmail, campoSenhaTexto;
  @FXML
  private Label labelStatus;
  @FXML
  private PasswordField campoSenhaOculto;
  @FXML
  private Button btnVoltar, btnVerSenha, btnLoginCliente;
  @FXML
  private ImageView iconBtnVer;
  private boolean controleBtnVer = true;
  private static long cpf;
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
  void loginCliente(ActionEvent event) {
    ClienteController mc = new ClienteController();
    Cliente cliente = mc.pesquisarCliente(Long.parseLong(campoEmail.getText()));
    if(cliente != null){
      if (cliente.getSenha().equals(campoSenhaTexto.getText())){
        setCpf(Long.parseLong(campoEmail.getText()));
        gerenciador.getStage().close();
        gerenciador.trocarCena("/visao/fxml/TelaPrincipalCliente.fxml");
      }else{
        //senha incorreta
        System.out.println(cliente.getSenha());
      }
    }else{
      System.out.println("crmv bla");
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
    gerenciador.trocarCena("/visao/fxml/TelaEscolhaCliente.fxml");
  }

  public void limparCampos() {
    campoEmail.clear();
    campoSenhaTexto.clear();
    campoSenhaOculto.clear();
  }

  public static long getCpf() {
    return cpf;
  }

  public static void setCpf(long cpf) {
    ControladorTelaLoginCliente.cpf = cpf;
  }

  
}
