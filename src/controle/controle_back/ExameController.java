package controle.controle_back;

import java.util.ArrayList;

import data.DAO.IExameDAO;
import data.JBDC.ExameDaoJdbc;
import modelo.Exame;

public class ExameController {
  IExameDAO exameDAO = new ExameDaoJdbc();

  public void cadastrarExame(String nome, String descricao) {
    Exame exame = new Exame(nome, descricao);
    exameDAO.createExame(exame);
  }

  public ArrayList<Exame> listarExames() {
    ArrayList<Exame> exames = (ArrayList<Exame>) exameDAO.getAllExame();
    return exames;
  }

  public Exame pesquisarExames(int codigo) {
    return exameDAO.readExame(codigo);
  }

  public void atualizarExame(String nome, String descricao) {
    Exame exame = new Exame(nome, descricao);
    exameDAO.updateExame(exame);
  }

  public void deletarExame(String nome, String descricao, int codigo) {
    Exame exame = new Exame(nome, descricao);
    exame.setCodExame(codigo);
    exameDAO.deleteExame(exame);
  }

}
