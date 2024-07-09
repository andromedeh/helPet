package controle.controle_back;

import java.util.ArrayList;

import data.DAO.IMedicoVeterinarioDAO;
import data.JBDC.MedicoVeterinarioDaoJdbc;
import modelo.MedicoVeterinario;

public class MedicoVeterinarioController {
    IMedicoVeterinarioDAO medVetDAO = new MedicoVeterinarioDaoJdbc();

    public void cadastrarMedicoVeterinario (String nome, long cpf, long telefone, String funcao, String email, String senha, int crmv) {
        MedicoVeterinario medVet = new MedicoVeterinario(nome, cpf, telefone, funcao, email, senha, crmv);
        medVetDAO.createMedicoVeterinario(medVet);
    }
    
    public ArrayList<MedicoVeterinario> listarMedicoVeterinario(){
        ArrayList<MedicoVeterinario> medVet = (ArrayList<MedicoVeterinario>) medVetDAO.getAllMedicoVeterinario();
        return medVet;
    }

    public MedicoVeterinario pesquisarMedicoVeterinarios (int crmv){
      return medVetDAO.readMedicoVeterinario(crmv);
    }

    public void atualizarMedicoVeterinario (String nome, long cpf, long telefone, String funcao, String email, String senha, int crmv) {
      MedicoVeterinario medVet = new MedicoVeterinario(nome, cpf, telefone, funcao, email, senha, crmv);
      medVetDAO.updateMedicoVeterinario(medVet);
    }

    public void deletarMedicoVeterinario (String nome, long cpf, long telefone, String funcao, String email, String senha, int crmv) {
      MedicoVeterinario medVet = new MedicoVeterinario(nome, cpf, telefone, funcao, email, senha, crmv);
      medVetDAO.deleteMedicoVeterinario(medVet);
    }


}
