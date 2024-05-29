package controle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import dados.DadosCliente;
import modelo.Cliente;

public class ControleCliente {
  private DadosCliente dadosCliente = new DadosCliente();

  public ArrayList<Cliente> getListaClientes() throws IOException, FileNotFoundException, ClassNotFoundException {
    return dadosCliente.listarCliente();
  }

  public void cadastrarCliente(String nome, String sobrenome, long cpf, long telefone, String email, String endereco,
      String senha,
      String confirmarSenha) throws IOException {
    Cliente c = new Cliente(nome, sobrenome, cpf, telefone, email, endereco, senha, confirmarSenha);
    dadosCliente.cadastrarCliente(c);
  }

  public Cliente procurarClienteCpf(long cpf) throws IOException, FileNotFoundException, ClassNotFoundException {
    Cliente c = dadosCliente.pesquisarClienteCpf(cpf);
    return c;
  }

  public Cliente procurarClienteTel(long telefone) throws IOException, FileNotFoundException, ClassNotFoundException {
    Cliente c = dadosCliente.pesquisarClienteTel(telefone);
    return c;
  }

  public Cliente procurarClienteEmail(String email) throws IOException, FileNotFoundException, ClassNotFoundException {
    Cliente c = dadosCliente.pesquisarClienteEmail(email);
    return c;
  }

  public boolean loginCliente(String email, String senha)
      throws IOException, FileNotFoundException, ClassNotFoundException {
    if (procurarClienteEmail(email) != null) {
      if (senha.equals(procurarClienteEmail(email).getSenha())) {
        return true;
      }
      return false;
    }
    return false;

  }
}