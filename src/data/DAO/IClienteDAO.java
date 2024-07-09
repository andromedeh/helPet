package data.DAO;

import java.util.List;

import modelo.Cliente;

public interface IClienteDAO {
    public List<Cliente> getAllCliente();
    public void createCliente(Cliente cliente);
    public Cliente readCliente(long cpf);
    public void updateCliente(Cliente cliente);
    public void deleteCliente(Cliente cliente);
}
