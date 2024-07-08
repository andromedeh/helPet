package DAO;

import java.util.List;

import modelo.Vacina;

public interface IVacinaDAO {
    public List<Vacina> getAllVacina();
    public void createVacina(Vacina vacina);
    public Vacina readVacina(int codigo);
    public void updateVacina(Vacina vacina);
    public void deleteVacina(Vacina vacina);
}
