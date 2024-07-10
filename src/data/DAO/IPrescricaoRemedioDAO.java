package data.DAO;

import java.util.List;

import modelo.PrescricaoRemedio;

public interface IPrescricaoRemedioDAO {
    public List<PrescricaoRemedio> getAllPrescricaoRemedio();
    public List<PrescricaoRemedio> getAllPrescricaoRemedioMedico(int crmv);
    public List<PrescricaoRemedio> getAllPrescricaoRemedioCliente(long cpf);
    public void createPrescricaoRemedio(PrescricaoRemedio PrescricaoRemedio);
    public PrescricaoRemedio readPrescricaoRemedio(String nomePet, long cpfDono, int crmvMedico, int codigoRemedio);
    public void updatePrescricaoRemedio(PrescricaoRemedio PrescricaoRemedio);
    public void deletePrescricaoRemedio(PrescricaoRemedio PrescricaoRemedio);
}