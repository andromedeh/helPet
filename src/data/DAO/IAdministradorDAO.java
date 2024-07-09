package data.DAO;

import java.util.List;

import modelo.Administrador;

public interface IAdministradorDAO {
    public List<Administrador> getAllAdministrador();
    public void createAdministrador(Administrador administrador);
    public Administrador readAdministrador(int codigo);
    public void updateAdministrador(Administrador administrador);
    public void deleteAdministrador(Administrador administrador);
    
}
