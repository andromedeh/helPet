package data.JBDC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.ConnectionFactory;
import data.DAO.IProfissionalDAO;
import modelo.Profissional;

public class ProfissionalDaoJdbc implements IProfissionalDAO {
    private Connection connection;

    @Override
    public List<Profissional> getAllProfissional() {
        List<Profissional> profissionais = null;
        String query = "select * from profissional";
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                profissionais = new ArrayList<Profissional>();
                while (resultSet.next()) {
                    Profissional p = new Profissional();
                    p.setNome(resultSet.getString("Nome_Profissional"));
                    p.setCpf(resultSet.getLong("CPF_Profissional"));
                    p.setTelefone(resultSet.getLong("Telefone_Profissional"));
                    p.setFuncao(resultSet.getString("Funcao_Profissional"));
                    p.setEmail(resultSet.getString("Email_Profissional"));
                    p.setSenha(resultSet.getString("Senha_Profissional"));
                    profissionais.add(p);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profissionais;
    }

    @Override
    public void createProfissional(Profissional profissional) {
        String query = "insert into profissional (Nome_Profissional, CPF_Profissional, Telefone_Profissional, Funcao_Profissional, Email_Profissional, Senha_Profissional) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, profissional.getNome());
            preparedStatement.setLong(2, profissional.getCpf());
            preparedStatement.setLong(3, profissional.getTelefone());
            preparedStatement.setString(4, profissional.getFuncao());
            preparedStatement.setString(5, profissional.getEmail());
            preparedStatement.setString(6, profissional.getSenha());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir Profissional");
        }
    }

    @Override
    public Profissional readProfissional(long cpf) {
        String query = "select * from profissional where CPF_Profissional=?";
        Connection connection = ConnectionFactory.concectBD();
        Profissional p = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, cpf);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                p = new Profissional();
                p.setNome(resultSet.getString("Nome_Profissional"));
                p.setCpf(resultSet.getLong("CPF_Profissional"));
                p.setTelefone(resultSet.getLong("Telefone_Profissional"));
                p.setFuncao(resultSet.getString("Funcao_Profissional"));
                p.setEmail(resultSet.getString("Email_Profissional"));
                p.setSenha(resultSet.getString("Senha_Profissional"));
            }
            resultSet.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar Profissional");
            ex.printStackTrace();
        }
        return p;
    }

    @Override
    public void updateProfissional(Profissional profissional) {
        String query = "update profissional set Nome_Profissional=?, Telefone_Profissional=?, Funcao_Profissional=?, Email_Profissional=?, Senha_Profissional=? where CPF_Profissional=?";
        PreparedStatement pst;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            pst = connection.prepareStatement(query);
            pst.setString(1, profissional.getNome());
            pst.setLong(2, profissional.getTelefone());
            pst.setString(3, profissional.getFuncao());
            pst.setString(4, profissional.getEmail());
            pst.setString(5, profissional.getSenha());
            pst.setLong(6, profissional.getCpf());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar Profissional");
        }
    }

    @Override
    public void deleteProfissional(Profissional profissional) {
        String query = "delete from profissional where CPF_Profissional=?";
        PreparedStatement pst;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            pst = connection.prepareStatement(query);
            pst.setLong(1, profissional.getCpf());
            pst.execute();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar Profissional");
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
