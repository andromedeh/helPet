package data.DAO;

import java.util.List;

import modelo.RealizaExame;

public interface IRealizaExameDAO {
    public List<RealizaExame> getAllRealizaExame();
    public List<RealizaExame> getAllRealizaExameMedico(int crmv);
    public List<RealizaExame> getAllRealizaExameCliente(long cpf);
    public void createRealizaExame(RealizaExame RealizaExame);
    public RealizaExame readRealizaExame(String nomePet, long cpfDono, int crmvMedico, int codigoExame);
    public void updateRealizaExame(RealizaExame RealizaExame);
    public void deleteRealizaExame(RealizaExame RealizaExame);
}
