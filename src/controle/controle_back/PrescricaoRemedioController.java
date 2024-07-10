package controle.controle_back;


import java.util.ArrayList;

import data.DAO.IPrescricaoRemedioDAO;
import data.JBDC.PrescricaoRemedioDaoJdbc;
import modelo.PrescricaoRemedio;

public class PrescricaoRemedioController {
    IPrescricaoRemedioDAO PrescricaoRemedioDAO = new PrescricaoRemedioDaoJdbc();

    public void cadastrarPrescricaoRemedio (String nomePet,long cpfDono, int crmvMedico, int codigoRemedio){
        PrescricaoRemedio PrescricaoRemedio = new PrescricaoRemedio(nomePet, cpfDono, crmvMedico, codigoRemedio);
        PrescricaoRemedioDAO.createPrescricaoRemedio(PrescricaoRemedio);
    }
    
    public ArrayList<PrescricaoRemedio> listarPrescricaoRemedios(){
        ArrayList<PrescricaoRemedio> PrescricaoRemedios = (ArrayList<PrescricaoRemedio>) PrescricaoRemedioDAO.getAllPrescricaoRemedio();
        return PrescricaoRemedios;
    }
    public ArrayList<PrescricaoRemedio> listarPrescricaoRemediosMedico(int crmv){
        ArrayList<PrescricaoRemedio> PrescricaoRemedios = (ArrayList<PrescricaoRemedio>) PrescricaoRemedioDAO.getAllPrescricaoRemedioMedico(crmv);
        return PrescricaoRemedios;
    }
    public ArrayList<PrescricaoRemedio> listarPrescricaoRemediosCliente(long cpf){
        ArrayList<PrescricaoRemedio> PrescricaoRemedios = (ArrayList<PrescricaoRemedio>) PrescricaoRemedioDAO.getAllPrescricaoRemedioCliente(cpf);
        return PrescricaoRemedios;
    }

    public PrescricaoRemedio pesquisarPrescricaoRemedios (String nomePet,long cpfDono, int crmvMedico, int codigoRemedio){
      return PrescricaoRemedioDAO.readPrescricaoRemedio(nomePet, cpfDono, crmvMedico, codigoRemedio);
    }
    

    public void atualizarPrescricaoRemedio (String nomePet,long cpfDono, int crmvMedico, int codigoRemedio){
      PrescricaoRemedio PrescricaoRemedio = new PrescricaoRemedio(nomePet, cpfDono, crmvMedico, codigoRemedio);
      PrescricaoRemedioDAO.updatePrescricaoRemedio(PrescricaoRemedio);
    }

    public void deletarPrescricaoRemedio (String nomePet,long cpfDono, int crmvMedico, int codigoRemedio){
      PrescricaoRemedio PrescricaoRemedio = new PrescricaoRemedio(nomePet, cpfDono, crmvMedico, codigoRemedio);
      PrescricaoRemedioDAO.deletePrescricaoRemedio(PrescricaoRemedio);
    }
}
