package data.DAO;

import java.util.List;

import modelo.Remedio;

public interface IRemedioDAO {
    public List<Remedio> getAllRemedio();
    public void createRemedio(Remedio remedio);
    public Remedio readRemedio(int codigo);
    public void updateRemedio(Remedio remedio);
    public void deleteRemedio(Remedio remedio);
    
}
