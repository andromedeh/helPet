package controle.controle_back;

import data.DAO.IClienteDAO;
import data.JBDC.ClienteDaoJdbc;

public class ClienteController {
    IClienteDAO clienteDAO = new ClienteDaoJdbc();
    public void cadastrarCliente (String nome, String sobrenome, long cpf, long telefone, String email, String endereco, String senha);
}
