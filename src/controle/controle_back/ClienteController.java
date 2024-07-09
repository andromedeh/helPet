package controle.controle_back;

import java.util.ArrayList;

import data.DAO.IClienteDAO;
import data.JBDC.ClienteDaoJdbc;
import modelo.Cliente;

public class ClienteController {
    IClienteDAO clienteDAO = new ClienteDaoJdbc();
    public void cadastrarCliente (String nome, String sobrenome, long cpf, long telefone, String email, String endereco, String senha){
        Cliente cliente = new Cliente(nome, sobrenome, cpf, telefone, email, endereco, senha);
        clienteDAO.createCliente(cliente);
    };

    public ArrayList<Cliente> listarCliente(){
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) clienteDAO.getAllCliente();
        return clientes;
    }
    public Cliente pesquisarCliente(long cpf){
        Cliente cliente = clienteDAO.readCliente(cpf);
        return cliente;
    }

    public void atualizarCliente (String nome, String sobrenome, long cpf, long telefone, String email, String endereco, String senha){
        Cliente cliente = new Cliente(nome, sobrenome, cpf, telefone, email, endereco, senha);
        clienteDAO.updateCliente(cliente);
    }
    public void deletarCliente (String nome, String sobrenome, long cpf, long telefone, String email, String endereco, String senha){
        Cliente cliente = new Cliente(nome, sobrenome, cpf, telefone, email, endereco, senha);
        clienteDAO.deleteCliente(cliente);
    }
}
