package controle;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import controle.controle_back.ConsultaController;
import controle.controle_back.MedicoVeterinarioController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaCadastrarConsulta extends ControladorBase implements Initializable {
  @FXML
  AnchorPane AnchorPaneTelaCadastroConsulta;

  @FXML
  Button btnCadastrarConsulta;

  @FXML
  TextField campoNomePet;

  @FXML
  Label labelStatusConsulta;

  @FXML
  TextField campoCPFDono;

  @FXML
  TextField campoCRMVMedico;

  @FXML
  DatePicker campoData;

  @FXML
  TextField campoHora;

  String nomePet;
  long cpfDono;
  String CRMV;
  String horario;
  DatePicker dataConsulta;
  ConsultaController consulta = new ConsultaController();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    AnchorPaneTelaCadastroConsulta.setVisible(true);

    if (ControladorTelaCadastroCliente.getCpf() != 0) {
      cpfDono = ControladorTelaCadastroCliente.getCpf();
    } else {
      cpfDono = ControladorTelaLoginCliente.getCpf();
    }
  }

  @FXML
  void cadastrarConsulta(ActionEvent event) {
    nomePet = campoNomePet.getText();
    CRMV = campoCRMVMedico.getText();
    horario = campoHora.getText();
    cpfDono = Long.parseLong(campoCPFDono.getText());
    MedicoVeterinarioController mvc = new MedicoVeterinarioController();
    System.out.println(mvc.pesquisarMedicoVeterinario(Integer.parseInt(CRMV)).getNome());
    Date date=Date.valueOf(safeGetDateFromDatePicker(campoData));
    

    if (nomePet.isEmpty() || cpfDono < 0 || CRMV.isEmpty() || horario.isEmpty()) {
      labelStatusConsulta.setText("Preencha todos os campos!");
      return;
    }

    if (!nomePet.matches("[a-zA-Z\\s]+")) {
        labelStatusConsulta.setText("O nome do pet deve conter apenas letras.");
      return;
    }
    if (!eInteiro(campoCRMVMedico.getText())) {
        labelStatusConsulta.setText("Entrada inválida para o CRMV. Por favor, insira um número válido.");
      return;
    }

    if (consulta.pesquisarConsultas(nomePet, cpfDono, Integer.parseInt(CRMV), date) != null) {
      labelStatusConsulta.setText("Nome do pet/ CPF do Dono / CRMV / data da consulta precisa ser diferente!");
      return;
    }

    consulta.cadastrarConsulta(nomePet, cpfDono, Integer.parseInt(CRMV), horario, date);
    

    AnchorPaneTelaCadastroConsulta.setVisible(true);
    btnCadastrarConsulta.setDisable(true);
    btnCadastrarConsulta.setVisible(true);
    labelStatusConsulta.setText("Cadastrado");
    limparCamposConsulta();

  }

  private boolean eInteiro(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  void limparCamposConsulta() {
    campoNomePet.setText("");
    campoCPFDono.setText("");
    campoCRMVMedico.setText("");
    campoHora.setText("");
  }
    public static LocalDate safeGetDateFromDatePicker(DatePicker datePicker) {
    return datePicker.getConverter().fromString(datePicker.getEditor().getText());
}
}

