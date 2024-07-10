package controle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import controle.controle_back.ClienteController;
import controle.controle_back.MedicoVeterinarioController;
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

  // NOMEAR CORRETAMENTE A TABELA E COLUNAS
  @FXML
  private TableView<Profissional> tabelaProfissionais;

  @FXML
  private TableColumn<Profissional, Long> colunaProfissionalCpf;

  @FXML
  private TableColumn<Profissional, String> colunaProfissionalNome;

  @FXML
  private TableColumn<Profissional, String> colunaProfissionalFuncao;

  @FXML
  private TableColumn<Profissional, String> colunaProfissionalCrmv;

  @FXML
  private Label labelAdm;

  @FXML // botoes da PaneClientes
  private Button btnExcluirCliente, btnAtualizarCliente;

  @FXML
  private TableView<?> tabelaAdmClientes;

  @FXML
  private TableColumn<?, ?> colunaCpfCliente;

  @FXML
  private TableColumn<?, ?> colunaNomeCliente;

  @FXML
  private TableColumn<?, ?> colunaNomePet;

  private static ProfissionalController profissional = new ProfissionalController();
  MedicoVeterinarioController medicoVeterinario = new MedicoVeterinarioController();
  ClienteController cliente = new ClienteController();

  private static ObservableList<Profissional> profissionais = FXCollections
      .observableArrayList(profissional.listarProfissional());

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    tabelaProfissionais.setItems(profissionais);
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
  void atualizarTabelaProfissional(ActionEvent event) {
    profissionais.setAll(profissional.listarProfissional());
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

  @FXML
  void excluirProfissional(ActionEvent event) {
    Profissional profissionalSelecionado = tabelaProfissionais.getSelectionModel().getSelectedItem();
    if (profissionalSelecionado != null) {
      String nome = profissionalSelecionado.getNome();
      Long cpf = profissionalSelecionado.getCpf();
      Long telefone = profissionalSelecionado.getTelefone();
      String funcao = profissionalSelecionado.getFuncao();
      String email = profissionalSelecionado.getEmail();
      String senha = profissionalSelecionado.getSenha();
      profissional.deletarProfissional(nome, cpf, telefone, funcao, email, senha);
      List<Long> cpfs = medicoVeterinario.listarCPFsMedicoVeterinario();
      if (cpfs.contains(cpf)) {
        System.out.println("É MEDICO");
        medicoVeterinario.deletarMedicoVeterinario(cpf);
      } else {
        System.out.println("Nao e medico");
      }

      atualizarTabelaProfissional(event);
    }
  }

  public void carregarTabelaProfissional() {
    // IMPLEMENTAR LOGICA DE CARREGAR TABELA
    colunaProfissionalCpf.setCellValueFactory(new PropertyValueFactory<Profissional, Long>("cpf"));
    colunaProfissionalNome.setCellValueFactory(new PropertyValueFactory<Profissional, String>("nome"));
    colunaProfissionalFuncao.setCellValueFactory(new PropertyValueFactory<Profissional, String>("funcao"));
    tabelaProfissionais.setItems(profissionais);
  }

  @FXML
  void atualizarTabelaCliente(ActionEvent event) {

  }

  public void carregarTabelaCliente() {

  }

  @FXML
  void excluirCliente(ActionEvent event) {

  }

}
