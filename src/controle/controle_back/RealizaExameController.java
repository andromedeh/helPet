package controle.controle_back;


import java.util.ArrayList;

import data.DAO.IRealizaExameDAO;
import data.JBDC.RealizaExameDaoJdbc;
import modelo.RealizaExame;

public class RealizaExameController {
    IRealizaExameDAO RealizaExameDAO = new RealizaExameDaoJdbc();

    public void cadastrarRealizaExame (String nomePet,long cpfDono, int crmvMedico, int codigoExame){
        RealizaExame RealizaExame = new RealizaExame(nomePet, cpfDono, crmvMedico, codigoExame);
        RealizaExameDAO.createRealizaExame(RealizaExame);
    }
    
    public ArrayList<RealizaExame> listarRealizaExames(){
        ArrayList<RealizaExame> RealizaExames = (ArrayList<RealizaExame>) RealizaExameDAO.getAllRealizaExame();
        return RealizaExames;
    }
    public ArrayList<RealizaExame> listarRealizaExamesMedico(int crmv){
        ArrayList<RealizaExame> RealizaExames = (ArrayList<RealizaExame>) RealizaExameDAO.getAllRealizaExameMedico(crmv);
        return RealizaExames;
    }
    public ArrayList<RealizaExame> listarRealizaExamesCliente(long cpf){
        ArrayList<RealizaExame> RealizaExames = (ArrayList<RealizaExame>) RealizaExameDAO.getAllRealizaExameCliente(cpf);
        return RealizaExames;
    }

    public RealizaExame pesquisarRealizaExames (String nomePet,long cpfDono, int crmvMedico, int codigoExame){
      return RealizaExameDAO.readRealizaExame(nomePet, cpfDono, crmvMedico, codigoExame);
    }
    

    public void atualizarRealizaExame (String nomePet,long cpfDono, int crmvMedico, int codigoExame){
      RealizaExame RealizaExame = new RealizaExame(nomePet, cpfDono, crmvMedico, codigoExame);
      RealizaExameDAO.updateRealizaExame(RealizaExame);
    }

    public void deletarRealizaExame (String nomePet,long cpfDono, int crmvMedico, int codigoExame){
      RealizaExame RealizaExame = new RealizaExame(nomePet, cpfDono, crmvMedico, codigoExame);
      RealizaExameDAO.deleteRealizaExame(RealizaExame);
    }
}
