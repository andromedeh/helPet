package controle.controle_back;

import java.sql.Date;
import java.util.ArrayList;

import data.DAO.IExameDAO;
import data.JBDC.ExameDaoJdbc;
import modelo.Exame;


public class ExameController {
    IExameDAO exameDAO = new ExameDaoJdbc();

    public void cadastrarExame (String nome, String descricao, Date data, String hora){
        Exame exame = new Exame(nome, descricao, data, hora);
        exameDAO.createExame(exame);
    }
    
    public ArrayList<Exame> listarExames(){
        ArrayList<Exame> exames = (ArrayList<Exame>) exameDAO.getAllExame();
        return exames;
    }

    public Exame pesquisarExames (int codigo){
      return exameDAO.readExame(codigo);
    }

    public void atualizarExame (String nome, String descricao, Date data, String hora){
      Exame exame = new Exame(nome, descricao, data, hora);
      exameDAO.updateExame(exame);
    }

    public void deletarExame (String nome, String descricao, Date data, String hora){
      Exame exame = new Exame(nome, descricao, data, hora);
      exameDAO.deleteExame(exame);
    }


}
