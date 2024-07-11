
package controle.controle_back;

import java.util.ArrayList;

import data.DAO.IRemedioDAO;
import data.JBDC.RemedioDaoJdbc;
import modelo.Remedio;

public class RemedioController {
  IRemedioDAO remedioDAO = new RemedioDaoJdbc();

  public void cadastrarRemedio(String nome, String descricao) {
    Remedio remedio = new Remedio(nome, descricao);
    remedioDAO.createRemedio(remedio);
  }

  public ArrayList<Remedio> listarRemedios() {
    ArrayList<Remedio> remedios = (ArrayList<Remedio>) remedioDAO.getAllRemedio();
    return remedios;
  }

  public Remedio pesquisarRemedios(int codigo) {
    return remedioDAO.readRemedio(codigo);
  }

  public void atualizarRemedio(String nome, String descricao) {
    Remedio remedio = new Remedio(nome, descricao);
    remedioDAO.updateRemedio(remedio);
  }

  public void deletarRemedio(String nome, String descricao, int codigo) {
    Remedio remedio = new Remedio(nome, descricao);
    remedio.setCodRemedio(codigo);
    remedioDAO.deleteRemedio(remedio);
  }

}
