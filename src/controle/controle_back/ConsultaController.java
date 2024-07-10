package controle.controle_back;

import java.sql.Date;
import java.util.ArrayList;

import data.DAO.IConsultaDao;
import data.JBDC.ConsultaDaoJdbc;
import modelo.Consulta;

public class ConsultaController {
    IConsultaDao ConsultaDAO = new ConsultaDaoJdbc();

    public void cadastrarConsulta (String nomePet,long cpfDono, int crmvMedico, String horario, Date date){
        Consulta Consulta = new Consulta(nomePet, cpfDono, crmvMedico, horario, date);
        ConsultaDAO.createConsulta(Consulta);
    }
    
    public ArrayList<Consulta> listarConsultas(){
        ArrayList<Consulta> Consultas = (ArrayList<Consulta>) ConsultaDAO.getAllConsulta();
        return Consultas;
    }
    public ArrayList<Consulta> listarConsultasMedico(int crmv){
        ArrayList<Consulta> Consultas = (ArrayList<Consulta>) ConsultaDAO.getAllConsultaMedico(crmv);
        return Consultas;
    }
    public ArrayList<Consulta> listarConsultasCliente(long cpf){
        ArrayList<Consulta> Consultas = (ArrayList<Consulta>) ConsultaDAO.getAllConsultaCliente(cpf);
        return Consultas;
    }

    public Consulta pesquisarConsultas (String nomePet, long cpfDono, int crmvMedico, Date date){
      return ConsultaDAO.readConsulta(nomePet, cpfDono, crmvMedico, date);
    }
    

    public void atualizarConsulta (String nomePet,long cpfDono, int crmvMedico, String horario, Date date){
      Consulta Consulta = new Consulta(nomePet, cpfDono, crmvMedico, horario, date);
      ConsultaDAO.updateConsulta(Consulta);
    }

    public void deletarConsulta (String nomePet,long cpfDono, int crmvMedico, String horario, Date date){
      Consulta Consulta = new Consulta(nomePet, cpfDono, crmvMedico, horario, date);
      ConsultaDAO.deleteConsulta(Consulta);
    }
}
