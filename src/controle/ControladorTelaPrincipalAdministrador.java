package controle;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import controle.controle_back.ClienteController;
import controle.controle_back.ExameController;
import controle.controle_back.ProfissionalController;
import controle.controle_back.RemedioController;
import controle.controle_back.VacinaController;
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
import modelo.Exame;
import modelo.Profissional;
import modelo.Remedio;
import modelo.Vacina;

public class ControladorTelaPrincipalAdministrador extends ControladorBase implements Initializable {
  @FXML
  private AnchorPane AnchorPaneLoginAdm;

  @FXML
  private Pane PaneMenu, PaneConteudo, PaneProfissionais, PaneClientes, PaneRemedios, PaneVacinas, PaneExames;

  @FXML // botoes da PaneMenu
  private Button btnGerenciarProfissionais, btnGerenciarClientes, btnSair, btnGerenciarVacinas, btnGerenciarExames,
      btnGerenciarRemedios, btnCadastrarExame, btnExcluirExame, btnAtulaizarExame, btnCadastrarRemedio,
      btnExcluirRemedio,
      btnAtulaizarRemedio, btnCadastrarVacina, btnExcluirVacina, btnAtulaizarVacina;

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

  // TABELA EXAME
  @FXML
  private TableView<Exame> tabelaAdmExames;

  @FXML
  private TableColumn<Exame, Integer> colunaAdmExameCod;

  @FXML
  private TableColumn<Exame, String> colunaAdmExameNome;

  @FXML
  private TableColumn<Exame, String> colunaAdmExameDescricao;

  // TABELA VACINAS
  @FXML
  private TableView<Vacina> tabelaAdmVacinas;

  @FXML
  private TableColumn<Vacina, Integer> colunaAdmVacinaCod;

  @FXML
  private TableColumn<Vacina, String> colunaAdmVacinaNome;

  @FXML
  private TableColumn<Vacina, String> colunaAdmVacinaDataReforco;

  // TABELA REMEDIOS
  @FXML
  private TableView<Remedio> tabelaAdmRemedios;

  @FXML
  private TableColumn<Remedio, Integer> colunaAdmRemedioCod;

  @FXML
  private TableColumn<Remedio, String> colunaAdmRemedioNome;

  @FXML
  private TableColumn<Remedio, String> colunaAdmRemedioDescricao;

  private static ProfissionalController profissional = new ProfissionalController();
  private static ClienteController cliente = new ClienteController();
  private static RemedioController remedio = new RemedioController();
  private static ExameController exame = new ExameController();
  private static VacinaController vacina = new VacinaController();

  private static ObservableList<Profissional> profissionais = FXCollections
      .observableArrayList(profissional.listarProfissional());

  private static ObservableList<Cliente> clientes = FXCollections.observableArrayList(cliente.listarCliente());

  private static ObservableList<Remedio> remedios = FXCollections
      .observableArrayList(remedio.listarRemedios());

  private static ObservableList<Vacina> vacinas = FXCollections
      .observableArrayList(vacina.listarVacinas());

  private static ObservableList<Exame> exames = FXCollections
      .observableArrayList(exame.listarExames());

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    carregarTabelaCliente();
    carregarTabelaProfissional();
    carregarTabelaRemedio();
    carregarTabelaVacina();
    carregarTabelaExame();
    visibilidade(true, false, false, false, false, false);
  }

  @FXML
  void gerenciarProfissionais(ActionEvent event) {
    visibilidade(false, true, false, false, false, false);
  }

  @FXML
  void gerenciarClientes(ActionEvent event) {
    visibilidade(false, false, true, false, false, false);
  }

  public void sair() {
    gerenciador.getStage().close();
    gerenciador.trocarCena("/visao/fxml/TelaInicial.fxml");
  }

  public void visibilidade(boolean conteudo, boolean profissionais, boolean clientes, boolean remedios, boolean vacinas,
      boolean exames) {
    PaneConteudo.setVisible(conteudo);
    PaneProfissionais.setVisible(profissionais);
    PaneClientes.setVisible(clientes);
    PaneRemedios.setVisible(remedios);
    PaneVacinas.setVisible(vacinas);
    PaneExames.setVisible(exames);
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

  @FXML
  void gerenciarExames(ActionEvent event) {
    visibilidade(false, false, false, false, false, true);
  }

  @FXML
  void cadastrarExame(ActionEvent event) {
    AnchorPane anchorPane;
    try {
      anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/fxml/TelaCadastroExame.fxml"));
      Stage secondStage = new Stage();
      Scene secondScene = new Scene(anchorPane);
      secondStage.setScene(secondScene);
      secondStage.show();
    } catch (IOException ex) {
    }
  }

  public void carregarTabelaExame() {
    colunaAdmExameCod.setCellValueFactory(new PropertyValueFactory<Exame, Integer>("codigo"));
    colunaAdmExameNome.setCellValueFactory(new PropertyValueFactory<Exame, String>("nome"));
    colunaAdmExameDescricao.setCellValueFactory(new PropertyValueFactory<Exame, String>("descricao"));
    tabelaAdmExames.setItems(exames);
  }

  @FXML
  void excluirExame(ActionEvent event) {
    Exame selecionado = tabelaAdmExames.getSelectionModel().getSelectedItem();
    if (selecionado != null) {
      String nome = selecionado.getNomeExame();
      String descricao = selecionado.getDescricao();
      exame.deletarExame(nome, descricao);
      atualizarTabelaExame(event);
    }
  }

  @FXML
  void atualizarTabelaExame(ActionEvent event) {
    exames = FXCollections.observableArrayList(exame.listarExames());
    tabelaAdmExames.setItems(exames);
  }

  @FXML
  void gerenciarRemedios(ActionEvent event) {
    visibilidade(false, false, false, true, false, false);

  }

  @FXML
  void cadastrarRemedio(ActionEvent event) {
    AnchorPane anchorPane;
    try {
      anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/fxml/TelaCadastroRemedio.fxml"));
      Stage secondStage = new Stage();
      Scene secondScene = new Scene(anchorPane);
      secondStage.setScene(secondScene);
      secondStage.show();
    } catch (IOException ex) {
    }
  }

  public void carregarTabelaRemedio() {
    colunaAdmRemedioCod.setCellValueFactory(new PropertyValueFactory<Remedio, Integer>("codigo"));
    colunaAdmRemedioNome.setCellValueFactory(new PropertyValueFactory<Remedio, String>("nome"));
    colunaAdmRemedioDescricao.setCellValueFactory(new PropertyValueFactory<Remedio, String>("descricao"));
    tabelaAdmRemedios.setItems(remedios);
  }

  @FXML
  void excluirRemedio(ActionEvent event) {
    Remedio selecionado = tabelaAdmRemedios.getSelectionModel().getSelectedItem();
    if (selecionado != null) {
      String nome = selecionado.getNome();
      String descricao = selecionado.getDescricao();
      remedio.deletarRemedio(nome, descricao);
      atualizarTabelaRemedio(event);
    }
  }

  @FXML
  void atualizarTabelaRemedio(ActionEvent event) {
    remedios = FXCollections.observableArrayList(remedio.listarRemedios());
    tabelaAdmRemedios.setItems(remedios);
  }

  @FXML
  void gerenciarVacinas(ActionEvent event) {
    visibilidade(false, false, false, false, true, false);
  }

  @FXML
  void cadastrarVacina(ActionEvent event) {
    AnchorPane anchorPane;
    try {
      anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/fxml/TelaCadastroVacina.fxml"));
      Stage secondStage = new Stage();
      Scene secondScene = new Scene(anchorPane);
      secondStage.setScene(secondScene);
      secondStage.show();
    } catch (IOException ex) {
    }
  }

  public void carregarTabelaVacina() {
    colunaAdmVacinaCod.setCellValueFactory(new PropertyValueFactory<Vacina, Integer>("codVacina"));
    colunaAdmVacinaNome.setCellValueFactory(new PropertyValueFactory<Vacina, String>("nomeVacina"));
    colunaAdmVacinaDataReforco.setCellValueFactory(new PropertyValueFactory<Vacina, String>("data_reforco"));
    tabelaAdmVacinas.setItems(vacinas);
  }

  @FXML
  void excluirVacina(ActionEvent event) {
    Vacina selecionado = tabelaAdmVacinas.getSelectionModel().getSelectedItem();
    if (selecionado != null) {
      String nome = selecionado.getNomeVacina();
      String data_Reforco = selecionado.getData_reforco();
      int codigo = selecionado.getCodVacina();
      vacina.deletarVacina(nome, data_Reforco, codigo);
      atualizarTabelaVacina(event);
    }
  }

  @FXML
  void atualizarTabelaVacina(ActionEvent event) {
    vacinas = FXCollections.observableArrayList(vacina.listarVacinas());
    tabelaAdmVacinas.setItems(vacinas);
  }
}
