package data.JBDC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.ConnectionFactory;
import data.DAO.IExameDAO;
import modelo.Exame;

public class ExameDaoJdbc implements IExameDAO {
    private Connection connection;

    @Override
    public List<Exame> getAllExame() {
        List<Exame> exames = null;
        String query = "select * from exame";
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                exames = new ArrayList<Exame>();
                while (resultSet.next()) {
                    Exame e = new Exame();
                    e.setCodExame(resultSet.getInt("Cod_Exame"));
                    e.setNomeExame(resultSet.getString("Nome_Exame"));
                    e.setDescricao(resultSet.getString("Descricao_Exame"));
                    e.setData(resultSet.getDate("Data_Exame"));
                    e.setHora(resultSet.getString("Hora_Exame"));
                    exames.add(e);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exames;
    }

    @Override
    public void createExame(Exame exame) {
        String query = "insert into exame (Nome_Exame, Descricao_Exame, Data_Exame, Hora_Exame) values (?,?,?,?)";
        PreparedStatement preparedStatement;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, exame.getNomeExame());
            preparedStatement.setString(2, exame.getDescricao());
            preparedStatement.setDate(3, exame.getData());
            preparedStatement.setString(4, exame.getHora());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir Exame");
        }
    }

    @Override
    public Exame readExame(int codigo) {
        String query = "select * from exame where Cod_Exame=?";
        Connection connection = ConnectionFactory.concectBD();
        Exame e = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                e = new Exame();
                e.setCodExame(resultSet.getInt("Cod_Exame"));
                e.setNomeExame(resultSet.getString("Nome_Exame"));
                e.setDescricao(resultSet.getString("Descricao_Exame"));
                e.setData(resultSet.getDate("Data_Exame"));
                e.setHora(resultSet.getString("Hora_Exame"));
            }
            resultSet.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao listar Exame");
            ex.printStackTrace();
        }
        return e;
    }

    @Override
    public void updateExame(Exame exame) {
        String query = "update exame set Nome_Exame=?, Descricao_Exame=?, Data_Exame=?, Hora_Exame=? where Cod_Exame=?";
        PreparedStatement pst;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            pst = connection.prepareStatement(query);
            pst.setString(1, exame.getNomeExame());
            pst.setString(2, exame.getDescricao());
            pst.setDate(3, exame.getData());
            pst.setString(4, exame.getHora());
            pst.setInt(5, exame.getCodExame());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar Exame");
        }
    }

    @Override
    public void deleteExame(Exame exame) {
        String query = "delete from exame where Cod_Exame =?";
        PreparedStatement pst;
        Connection connection;

        try {
            connection = ConnectionFactory.concectBD();
            pst = connection.prepareStatement(query);
            pst.setInt(1, exame.getCodExame());
            pst.execute();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar Exame");
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
