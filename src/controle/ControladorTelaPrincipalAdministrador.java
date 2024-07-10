package controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controle.controle_back.ClienteController;
import controle.controle_back.ProfissionalController;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Profissional;

public class ControladorTelaPrincipalAdministrador extends ControladorBase implements Initializable {
  @FXML
  private AnchorPane AnchorPaneLoginAdm;

  @FXML
  private Pane PaneMenu, PaneConteudo, PaneProfissionais, PaneClientes;

  @FXML // botoes da PaneMenu
  private Button btnGerenciarProfissionais, btnGerenciarClientes, btnSair;

  @FXML // botoes da PaneProfissionais
  private Button btnCadastrarProfissional, btnExcluirProfissional, btnAtualizarProfissional;

  @FXML
  private Label labelAdm;

  @FXML // botoes da PaneClientes
  private Button btnExcluirCliente, btnAtualizarCliente;

  // TABELA PROFISSIONAIS
  @FXML
  private TableView<Profissional> tabelaProfissionais;

  @FXML
  private TableColumn<Profissional, Long> colunaProfissionalCpf;

  @FXML
  private TableColumn<Profissional, String> colunaProfissionalNome;

  @FXML
  private TableColumn<Profissional, String> colunaProfissionalFuncao;

  // TABELA CLIENTES
  @FXML
  private TableView<Cliente> tabelaAdmClientes;

  @FXML
  private TableColumn<Cliente, Long> colunaCpfCliente;

  @FXML
  private TableColumn<Cliente, String> colunaNomeCliente;

  @FXML
  private TableColumn<Cliente, String> colunaSobrenomeCliente;

  private static ProfissionalController profissional = new ProfissionalController();
  private static ClienteController cliente = new ClienteController();

  private static ObservableList<Profissional> profissionais = FXCollections
      .observableArrayList(profissional.listarProfissional());

  private static ObservableList<Cliente> clientes = FXCollections.observableArrayList(cliente.listarCliente());

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    carregarTabelaCliente();
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

  public void sair() {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaInicial.fxml");
  }

  public void visibilidade(boolean conteudo, boolean profissionais, boolean clientes) {
    PaneConteudo.setVisible(conteudo);
    PaneProfissionais.setVisible(profissionais);
    PaneClientes.setVisible(clientes);
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
    } catch (IOException ex) {
    }
  }

  public void carregarTabelaProfissional() {
    colunaProfissionalCpf.setCellValueFactory(new PropertyValueFactory<Profissional, Long>("cpf"));
    colunaProfissionalNome.setCellValueFactory(new PropertyValueFactory<Profissional, String>("nome"));
    colunaProfissionalFuncao.setCellValueFactory(new PropertyValueFactory<Profissional, String>("funcao"));
    tabelaProfissionais.setItems(profissionais);
  }

  @FXML
  void excluirProfissional(ActionEvent event) {
    Profissional selecionado = tabelaProfissionais.getSelectionModel().getSelectedItem();
    if (selecionado != null) {
      Long cpf = selecionado.getCpf();
      String nome = selecionado.getNome();
      Long telefone = selecionado.getTelefone();
      String funcao = selecionado.getFuncao();
      String email = selecionado.getEmail();
      String senha = selecionado.getSenha();
      profissional.deletarProfissional(nome, cpf, telefone, funcao, email, senha);
      atualizarTabelaProfissional(event);
    }
  }

  @FXML
  void atualizarTabelaProfissional(ActionEvent event) {
    profissionais = FXCollections.observableArrayList(profissional.listarProfissional());
    tabelaProfissionais.setItems(profissionais);
  }

  public void carregarTabelaCliente() {
    colunaCpfCliente.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("cpf"));
    colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
    colunaSobrenomeCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("sobrenome"));
    tabelaAdmClientes.setItems(clientes);
  }

  @FXML
  void excluirCliente(ActionEvent event) {
    Cliente selecionado = tabelaAdmClientes.getSelectionModel().getSelectedItem();
    if (selecionado != null) {
      String nome = selecionado.getNome();
      String sobrenome = selecionado.getSobrenome();
      Long cpf = selecionado.getCpf();
      Long telefone = selecionado.getTelefone();
      String email = selecionado.getEmail();
      String endereco = selecionado.getEndereco();
      String senha = selecionado.getSenha();
      cliente.deletarCliente(nome, sobrenome, cpf, telefone, email, endereco, senha);
      atualizarTabelaCliente(event);
    }
  }

  @FXML
  void atualizarTabelaCliente(ActionEvent event) {
    clientes = FXCollections.observableArrayList(cliente.listarCliente());
    tabelaAdmClientes.setItems(clientes);
  }

}
