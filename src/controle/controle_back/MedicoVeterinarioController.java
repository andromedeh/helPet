package controle.controle_back;

import java.util.ArrayList;
import java.util.List;

import data.DAO.IMedicoVeterinarioDAO;
import data.DAO.IProfissionalDAO;
import data.JBDC.MedicoVeterinarioDaoJdbc;
import data.JBDC.ProfissionalDaoJdbc;
import modelo.MedicoVeterinario;
import modelo.Profissional;

public class MedicoVeterinarioController {
  IMedicoVeterinarioDAO medVetDAO = new MedicoVeterinarioDaoJdbc();
  IProfissionalDAO profissionalDAO = new ProfissionalDaoJdbc();

  public void cadastrarMedicoVeterinario(String nome, long cpf, long telefone, String funcao, String email,
      String senha, int crmv) {
    MedicoVeterinario medVet = new MedicoVeterinario(nome, cpf, telefone, funcao, email, senha, crmv);
    Profissional profissional = new Profissional(nome, cpf, telefone, funcao, email, senha);
    medVetDAO.createMedicoVeterinario(medVet);
    profissionalDAO.createProfissional(profissional);
  }

  public ArrayList<MedicoVeterinario> listarMedicoVeterinario() {
    ArrayList<MedicoVeterinario> medVet = (ArrayList<MedicoVeterinario>) medVetDAO.getAllMedicoVeterinario();
    return medVet;
  }

  public List<Long> listarCPFsMedicoVeterinario() {
    List<MedicoVeterinario> medicos = medVetDAO.getAllMedicoVeterinario();
    List<Long> cpfs = new ArrayList<>();
    for (MedicoVeterinario medico : medicos) {
      cpfs.add(medico.getCpf());
    }
    return cpfs;
  }

  public MedicoVeterinario pesquisarMedicoVeterinarios(int crmv) {
    return medVetDAO.readMedicoVeterinario(crmv);
  }

  public void atualizarMedicoVeterinario(String nome, long cpf, long telefone, String funcao, String email,
      String senha, int crmv) {
    MedicoVeterinario medVet = new MedicoVeterinario(nome, cpf, telefone, funcao, email, senha, crmv);
    medVetDAO.updateMedicoVeterinario(medVet);
  }

  public void deletarMedicoVeterinario(Long cpf) {
    medVetDAO.deleteMedicoVeterinario(cpf);
  }

}
