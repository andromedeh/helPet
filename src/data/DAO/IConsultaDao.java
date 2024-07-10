package data.DAO;

import java.sql.Date;
import java.util.List;

import modelo.Consulta;

public interface IConsultaDao {
    public List<Consulta> getAllConsulta();
    public List<Consulta> getAllConsultaMedico(int crmv);
    public List<Consulta> getAllConsultaCliente(long cpf);
    public void createConsulta(Consulta Consulta);
    public Consulta readConsulta(String nomePet, long cpfDono, int crmvMedico,Date data);
    public void updateConsulta(Consulta Consulta);
    public void deleteConsulta(Consulta Consulta);

}
