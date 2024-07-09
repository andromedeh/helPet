package data.DAO;

import java.util.List;

import modelo.MedicoVeterinario;

public interface IMedicoVeterinarioDAO {
    public List<MedicoVeterinario> getAllMedicoVeterinario();
    public void createMedicoVeterinario(MedicoVeterinario MedicoVeterinario);
    public MedicoVeterinario readMedicoVeterinario(int crmv);
    public void updateMedicoVeterinario(MedicoVeterinario MedicoVeterinario);
    public void deleteMedicoVeterinario(MedicoVeterinario MedicoVeterinario);
    
}
