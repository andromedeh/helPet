package data.JBDC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.ConnectionFactory;
import data.DAO.IRemedioDAO;
import modelo.Remedio;

public class RemedioDaoJdbc implements IRemedioDAO {
    private Connection connection;

    @Override
    public List<Remedio> getAllRemedio() {
        List<Remedio> remedios = null;
        String query = "select * from remedio";
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                remedios = new ArrayList<Remedio>();
                while (resultSet.next()) {
                    Remedio r = new Remedio();
                    r.setCodRemedio(resultSet.getInt("Cod_Remedio"));
                    r.setNome(resultSet.getString("Nome_Remedio"));
                    r.setDescricao(resultSet.getString("Descricao_Remedio"));
                    remedios.add(r);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return remedios;
    }

    @Override
    public void createRemedio(Remedio remedio) {
        String query = "insert into remedio (Nome_Remedio, Descricao_Remedio) values (?,?)";
        PreparedStatement preparedStatement;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, remedio.getNome());
            preparedStatement.setString(2, remedio.getDescricao());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir Remedio");
        }
    }

    @Override
    public Remedio readRemedio(int codigo) {
        String query = "select * from remedio where Cod_Remedio=?";
        Connection connection = ConnectionFactory.concectBD();
        Remedio r = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                r = new Remedio();
                r.setCodRemedio(resultSet.getInt("Cod_Remedio"));
                r.setNome(resultSet.getString("Nome_Remedio"));
                r.setDescricao(resultSet.getString("Descricao_Remedio"));
            }
            resultSet.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao listar Remedio");
            ex.printStackTrace();
        }
        return r;
    }

    @Override
    public void updateRemedio(Remedio remedio) {
        String query = "update remedio set Nome_Remedio=?, Descricao_Remedio=? where Cod_Remedio=?";
        PreparedStatement pst;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            pst = connection.prepareStatement(query);
            pst.setString(1, remedio.getNome());
            pst.setString(2, remedio.getDescricao());
            pst.setInt(3, remedio.getCodRemedio());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar Remedio");
        }
    }

    @Override
    public void deleteRemedio(Remedio remedio) {
        String query = "delete from remedio where Cod_Remedio =?";
        PreparedStatement pst;
        Connection connection;

        try {
            connection = ConnectionFactory.concectBD();
            pst = connection.prepareStatement(query);
            pst.setInt(1, remedio.getCodRemedio());
            pst.execute();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar Remedio");
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
