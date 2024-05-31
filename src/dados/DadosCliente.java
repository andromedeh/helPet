package dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelo.Cliente;

public class DadosCliente {
  private final String arquivoCliente = "clientes.ser";

  public void cadastrarCliente(Cliente c) throws FileNotFoundException, IOException {
    ArrayList<Cliente> clientes = (ArrayList<Cliente>) listarCliente();
    clientes.add(c);
    try {
      FileOutputStream fluxo = new FileOutputStream(arquivoCliente);
      ObjectOutputStream escreverObj = new ObjectOutputStream(fluxo);
      escreverObj.writeObject(clientes);
      escreverObj.close();
    } catch (FileNotFoundException ex) {
      System.out.println(ex.getMessage());
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  public ArrayList<Cliente> listarCliente() throws IOException {
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    File arq = new File(arquivoCliente);
    if (!arq.exists()) {
      arq.createNewFile();
      return clientes;
    }
    FileInputStream fluxo;
    ObjectInputStream lerObj = null;
    try {
      fluxo = new FileInputStream(arq);
      lerObj = new ObjectInputStream(fluxo);
      clientes = (ArrayList<Cliente>) lerObj.readObject();
      fluxo.close();
    } catch (FileNotFoundException ex) {
      System.out.print(ex.getMessage());
    } catch (ClassNotFoundException | IOException ex) {
      System.out.print(ex.getMessage());
    }
    return clientes;
  }

  public Cliente pesquisarClienteCpf(long cpf)
      throws IOException, FileNotFoundException, ClassNotFoundException {
    Cliente flag = null;
    for (int i = 0; i < listarCliente().size(); i++) {
      if (cpf == listarCliente().get(i).getCpf()) {
        flag = listarCliente().get(i);
      }
    }
    return flag;
  }

  public Cliente pesquisarClienteTel(long telefone)
      throws IOException, FileNotFoundException, ClassNotFoundException {
    Cliente flag = null;
    for (int i = 0; i < listarCliente().size(); i++) {
      if (telefone == listarCliente().get(i).getTelefone()) {
        flag = listarCliente().get(i);
      }
    }
    return flag;
  }

  public Cliente pesquisarClienteEmail(String email)
      throws IOException, FileNotFoundException, ClassNotFoundException {
    Cliente flag = null;
    ArrayList<Cliente> clientes = listarCliente();
    for (Cliente cliente : clientes) {
      if (email.equals(cliente.getEmail())) {
        flag = cliente;
        break;
      }
    }
    return flag;
  }
}
