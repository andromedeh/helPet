package controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControladorTelaPrincipalAdministrador extends ControladorBase implements Initializable {
  @FXML
  private AnchorPane AnchorPaneLoginAdm;

  @FXML
  private Pane PaneMenu, PaneConteudo, PaneProfissionais, PaneClientes;

  @FXML // botoes da PaneMenu
  private Button btnGerenciarProfissionais, btnGerenciarClientes, btnSair;

  @FXML // botoes da PaneProfissionais
  private Button btnCadastrarProfissional, btnExcluirProfissional, btnAtualizarProfissional;

  // NOMEAR CORRETAMENTE A TABELA E COLUNAS
  @FXML
  private TableView<?> tabelaProfissionais;

  @FXML
  private TableColumn<?,?> cpf;

  @FXML
  private TableColumn<?,?> nome;

  @FXML
  private TableColumn<?,?> funcao;

  @FXML 
  private Label labelAdm;

  @FXML // botoes da PaneClientes
  private Button btnExcluirCliente, btnAtualizarCliente;

  @FXML
  private TableView<?> tabelaClientes;

  @FXML
  private TableColumn<?, ?> cpfCliente;

  @FXML
  private TableColumn<?, ?> nomeCliente;

  @FXML
  private TableColumn<?, ?> nomePet;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    carregarTabelaCliente(); // IMPLEMENTACAO PENDENTE
    carregarTabelaProfissional();
    visibilidade(true, false, false);
  }

  @FXML
  void gerenciarProfissionais(ActionEvent event) {
    visibilidade(false, true, false);
  }

  @FXML
  void gerenciarClientes(ActionEvent event) {
    visibilidade(false, false, true);
  }
  
  public void sair (){
    gerenciador.getStage().close();
		gerenciador.trocarCena("/visao/fxml/TelaInicial.fxml");
  }

  public void visibilidade (boolean conteudo, boolean profissionais, boolean clientes){
    PaneConteudo.setVisible(conteudo);
    PaneProfissionais.setVisible(profissionais);
    PaneClientes.setVisible(clientes);
  }

  // PANE GERENCIAR PROFISSINAIS
  @FXML
  void atualizarTabelaProfissional(ActionEvent event) {
    // IMPLEMENTAR LOGICA DE ATUALIZAR TABELA
  }

  @FXML
  void cadastrarProfissional(ActionEvent event) {
    AnchorPane anchorPane;
      try {
        anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/fxml/TelaCadastroProfissionais.fxml"));
        Stage secondStage = new Stage();
        Scene secondScene = new Scene(anchorPane);
        secondStage.setScene(secondScene);
        secondStage.show();
      } catch (IOException ex) {}
  }

  @FXML
  void excluirProfissional(ActionEvent event) {
    // IMPLEMENTAR LOGICA DE EXCLUIR LINHA DA TABELA
  }

  public void carregarTabelaProfissional(){
    // IMPLEMENTAR LOGICA DE CARREGAR TABELA
  }

  // PANE GERENCIAR CLIENTES
  @FXML
  void atualizarTabelaCliente(ActionEvent event) {

  }

  public void carregarTabelaCliente(){
    // IMPLEMENTAR LOGICA DE CARREGAR TABELA
  }

  @FXML
  void excluirCliente(ActionEvent event) {
    // IMPLEMENTAR LOGICA DE EXCLUIR LINHA DA TABELA
  }

}
