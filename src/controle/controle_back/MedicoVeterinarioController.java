package controle.controle_back;

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
    Profissional profissional = new Profissional(nome, cpf, telefone, funcao, email, senha);
    MedicoVeterinario medVet = new MedicoVeterinario(nome, cpf, telefone, funcao, email, senha, crmv);
    // Primeiro cria o profissional
    profissionalDAO.createProfissional(profissional);
    // Depois cria o médico veterinário associado
    medVetDAO.createMedicoVeterinario(medVet);
  }

  public List<MedicoVeterinario> listarMedicoVeterinario() {
    return medVetDAO.getAllMedicoVeterinario();
  }

  public MedicoVeterinario pesquisarMedicoVeterinario(int crmv) {
    return medVetDAO.readMedicoVeterinario(crmv);
  }

  public void atualizarMedicoVeterinario(String nome, long cpf, long telefone, String funcao, String email,
      String senha, int crmv) {
    Profissional profissional = new Profissional(nome, cpf, telefone, funcao, email, senha);
    MedicoVeterinario medVet = new MedicoVeterinario(nome, cpf, telefone, funcao, email, senha, crmv);

    // Atualiza o profissional
    profissionalDAO.updateProfissional(profissional);
    // Atualiza o médico veterinário
    medVetDAO.updateMedicoVeterinario(medVet);
  }

  public void deletarMedicoVeterinario(int crmv) {
    MedicoVeterinario medVet = medVetDAO.readMedicoVeterinario(crmv);
    if (medVet != null) {
      // Deleta o médico veterinário
      medVetDAO.deleteMedicoVeterinario(crmv);
      // Deleta o profissional associado
      profissionalDAO.deleteProfissional(new Profissional(medVet.getNome(), medVet.getCpf(), medVet.getTelefone(),
          medVet.getFuncao(), medVet.getEmail(), medVet.getSenha()));
    }
  }
}
