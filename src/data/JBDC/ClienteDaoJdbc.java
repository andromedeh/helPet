package data.JBDC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.ConnectionFactory;
import data.DAO.IClienteDAO;
import modelo.Cliente;

public class ClienteDaoJdbc implements IClienteDAO {
    private Connection connection;

    @Override
    public List<Cliente> getAllCliente() {
        List<Cliente> clientes = null;
        String query = "select * from cliente";
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                clientes = new ArrayList<Cliente>();
                while (resultSet.next()) {
                    Cliente c = new Cliente();
                    c.setNome(resultSet.getString("Nome_Cliente"));
                    c.setSobrenome(resultSet.getString("Sobrenome_Cliente"));
                    c.setCpf(resultSet.getLong("CPF_Cliente"));
                    c.setTelefone(resultSet.getLong("Telefone_Cliente"));
                    c.setEmail(resultSet.getString("Email_Cliente"));
                    c.setEndereco(resultSet.getString("Endereco_Cliente"));
                    c.setSenha(resultSet.getString("Senha_Cliente"));
                    clientes.add(c);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public void createCliente(Cliente cliente) {
        String query = "insert into cliente (Nome_Cliente, Sobrenome_Cliente, CPF_Cliente, Telefone_Cliente, Email_Cliente, Endereco_Cliente, Senha_Cliente) values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getSobrenome());
            preparedStatement.setLong(3, cliente.getCpf());
            preparedStatement.setLong(4, cliente.getTelefone());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setString(6, cliente.getEndereco());
            preparedStatement.setString(7, cliente.getSenha());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir Cliente");
        }
    }

    @Override
    public Cliente readCliente(long cpf) {
        String query = "select * from cliente where cpf_Cliente=?";
        Connection connection = ConnectionFactory.concectBD();
        Cliente c = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, cpf);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                c = new Cliente();
                c.setNome(resultSet.getString("Nome_Cliente"));
                c.setSobrenome(resultSet.getString("Sobrenome_Cliente"));
                c.setCpf(resultSet.getLong("CPF_Cliente"));
                c.setTelefone(resultSet.getLong("Telefone_Cliente"));
                c.setEmail(resultSet.getString("Email_Cliente"));
                c.setEndereco(resultSet.getString("Endereco_Cliente"));
                c.setSenha(resultSet.getString("Senha_Cliente"));
            }
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar Cliente");
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void updateCliente(Cliente cliente) {
        String query = "update cliente set Nome_Cliente=?, Sobrenome_Cliente=?, Telefone_Cliente=?, _Cliente=?, Endereco_Cliente=?, Senha_Cliente=? where cpf_Cliente=?";
        PreparedStatement pst;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            pst = connection.prepareStatement(query);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getSobrenome());
            pst.setLong(3, cliente.getTelefone());
            pst.setString(4, cliente.getEmail());
            pst.setString(5, cliente.getEndereco());
            pst.setString(6, cliente.getSenha());
            pst.setLong(7, cliente.getCpf());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar Cliente");
        }
    }

    @Override
    public void deleteCliente(Cliente cliente) {
        String query = "delete from cliente where cpf_Cliente =?";
        PreparedStatement pst;
        Connection connection;

        try {
            connection = ConnectionFactory.concectBD();
            pst = connection.prepareStatement(query);
            pst.setLong(1, cliente.getCpf());
            pst.execute();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar Cliente");
        }
    }

    private ResultSet statementExQuery(Connection connection, String query) throws SQLException {
        Statement statement;
        ResultSet resultSet = null;
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        return resultSet;
    }
}
