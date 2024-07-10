package controle.controle_back;

import java.sql.Date;
import java.util.ArrayList;

import data.DAO.IVacinaDAO;
import data.JBDC.VacinaDaoJdbc;
import modelo.Vacina;

public class VacinaController {
  IVacinaDAO vacinaDAO = new VacinaDaoJdbc();

  public void cadastrarVacina(String nome, String data_reforco) {
    Vacina vacina = new Vacina(nome, data_reforco);
    vacinaDAO.createVacina(vacina);
  }

  public ArrayList<Vacina> listarVacinas() {
    ArrayList<Vacina> vacinas = (ArrayList<Vacina>) vacinaDAO.getAllVacina();
    return vacinas;
  }

  public Vacina pesquisarVacinas(int codigo) {
    return vacinaDAO.readVacina(codigo);
  }

  public void atualizarVacina(String nome, String data_reforco) {
    Vacina vacina = new Vacina(nome, data_reforco);
    vacinaDAO.updateVacina(vacina);
  }

  public void deletarVacina(String nome, String data_reforco, int codigo) {
    Vacina vacina = new Vacina(nome, data_reforco);
    vacina.setCodVacina(codigo);
    vacinaDAO.deleteVacina(vacina);

  }

}
