package controle.controle_back;


import java.sql.Date;
import java.util.ArrayList;

import data.DAO.IAplicaVacinaDAO;
import data.JBDC.AplicaVacinaDaoJdbc;
import modelo.AplicaVacina;

public class AplicaVacinaController {
    IAplicaVacinaDAO AplicaVacinaDAO = new AplicaVacinaDaoJdbc();

    public void cadastrarAplicaVacina (String nomePet,long cpfDono, int crmvMedico, int codigoVacina, Date data_aplicacao, Date data_reforco){
        AplicaVacina AplicaVacina = new AplicaVacina(nomePet, cpfDono, crmvMedico, codigoVacina, data_aplicacao, data_reforco);
        AplicaVacinaDAO.createAplicaVacina(AplicaVacina);
    }
    
    public ArrayList<AplicaVacina> listarAplicaVacinas(){
        ArrayList<AplicaVacina> AplicaVacinas = (ArrayList<AplicaVacina>) AplicaVacinaDAO.getAllAplicaVacina();
        return AplicaVacinas;
    }
    public ArrayList<AplicaVacina> listarAplicaVacinasMedico(int crmv){
        ArrayList<AplicaVacina> AplicaVacinas = (ArrayList<AplicaVacina>) AplicaVacinaDAO.getAllAplicaVacinaMedico(crmv);
        return AplicaVacinas;
    }
    public ArrayList<AplicaVacina> listarAplicaVacinasCliente(long cpf){
        ArrayList<AplicaVacina> AplicaVacinas = (ArrayList<AplicaVacina>) AplicaVacinaDAO.getAllAplicaVacinaCliente(cpf);
        return AplicaVacinas;
    }

    public AplicaVacina pesquisarAplicaVacinas (String nomePet,long cpfDono, int crmvMedico, int codigoVacina){
      return AplicaVacinaDAO.readAplicaVacina(nomePet, cpfDono, crmvMedico, codigoVacina);
    }
    

    public void atualizarAplicaVacina(String nomePet,long cpfDono, int crmvMedico, int codigoVacina, Date data_aplicacao, Date data_reforco){
      AplicaVacina AplicaVacina = new AplicaVacina(nomePet, cpfDono, crmvMedico, codigoVacina, data_aplicacao, data_reforco);
      AplicaVacinaDAO.updateAplicaVacina(AplicaVacina);
    }

    public void deletarAplicaVacina (String nomePet,long cpfDono, int crmvMedico, int codigoVacina, Date data_aplicacao, Date data_reforco){
      AplicaVacina AplicaVacina = new AplicaVacina(nomePet, cpfDono, crmvMedico, codigoVacina, data_aplicacao, data_reforco);
      AplicaVacinaDAO.deleteAplicaVacina(AplicaVacina);
    }
}
