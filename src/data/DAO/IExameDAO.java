package data.DAO;

import java.util.List;

import modelo.Exame;

public interface IExameDAO {
    public List<Exame> getAllExame();
    public void createExame(Exame exame);
    public Exame readExame(int codigo);
    public void updateExame(Exame exame);
    public void deleteExame(Exame exame);
    
}
