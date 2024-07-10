package data.DAO;

import java.util.List;

import modelo.AplicaVacina;

public interface IAplicaVacinaDAO {
    public List<AplicaVacina> getAllAplicaVacina();
    public List<AplicaVacina> getAllAplicaVacinaMedico(int crmv);
    public List<AplicaVacina> getAllAplicaVacinaCliente(long cpf);
    public void createAplicaVacina(AplicaVacina AplicaVacina);
    public AplicaVacina readAplicaVacina(String nomePet, long cpfDono, int crmvMedico, int codigoVacina);
    public void updateAplicaVacina(AplicaVacina AplicaVacina);
    public void deleteAplicaVacina(AplicaVacina AplicaVacina);
}