package controle.controle_back;

import java.util.ArrayList;

import data.DAO.IProfissionalDAO;
import data.JBDC.ProfissionalDaoJdbc;
import modelo.Profissional;

public class ProfissionalController {
  IProfissionalDAO ProfissionalDAO = new ProfissionalDaoJdbc();

  public void cadastrarProfissional(String nome, long cpf, long telefone, String funcao, String email, String senha) {
    Profissional profissional = new Profissional(nome, cpf, telefone, funcao, email, senha);
    ProfissionalDAO.createProfissional(profissional);
  }

  public ArrayList<Profissional> listarProfissional() {
    ArrayList<Profissional> profissionals = (ArrayList<Profissional>) ProfissionalDAO.getAllProfissional();
    return profissionals;
  }

  public Profissional pesquisarProfissionals(long cpf) {
    return ProfissionalDAO.readProfissional(cpf);
  }

  public void atualizarProfissional(String nome, long cpf, long telefone, String funcao, String email, String senha) {
    Profissional Profissional = new Profissional(nome, cpf, telefone, funcao, email, senha);
    ProfissionalDAO.updateProfissional(Profissional);
  }

  public void deletarProfissional(String nome, long cpf, long telefone, String funcao, String email, String senha) {
    Profissional Profissional = new Profissional(nome, cpf, telefone, funcao, email, senha);
    ProfissionalDAO.deleteProfissional(Profissional);
  }

}
