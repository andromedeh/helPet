package data.JBDC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.ConnectionFactory;
import data.DAO.IVacinaDAO;
import modelo.Vacina;

public class VacinaDaoJdbc implements IVacinaDAO {
    private Connection connection;

    @Override
    public List<Vacina> getAllVacina() {
        List<Vacina> vacinas = null;
        String query = "select * from vacina";
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                vacinas = new ArrayList<Vacina>();
                while (resultSet.next()) {
                    Vacina v = new Vacina();
                    v.setCodVacina(resultSet.getInt("Cod_Vacina"));
                    v.setNomeVacina(resultSet.getString("Nome_Vacina"));
                    v.setData_reforco(resultSet.getString("data_reforco_Vacina"));
                    vacinas.add(v);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacinas;
    }

    @Override
    public void createVacina(Vacina vacina) {
        String query = "insert into vacina (Nome_Vacina,Data_reforco_vacina) values (?,?)";
        PreparedStatement preparedStatement;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            preparedStatement = connection.prepareStatement(query);
            /* DUVIDA: FALTA "Cod_Vacina" ? */
            preparedStatement.setString(1, vacina.getNomeVacina());
            preparedStatement.setString(2, vacina.getData_reforco());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir Vacina");
        }
    }

    @Override
    public Vacina readVacina(int codigo) {
        String query = "select * from vacina where Cod_Vacina=?";
        Connection connection = ConnectionFactory.concectBD();
        Vacina v = null;
        try {
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                v = new Vacina();
                v.setNomeVacina(resultSet.getString("Nome_Vacina"));
                v.setData_reforco(resultSet.getString("data_reforco_Vacina"));
            }
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar Vacina");
            e.printStackTrace();
        }
        return v;

    }

    @Override
    public void updateVacina(Vacina vacina) {
        String query = "update vacina set Nome_Vacina=?,Data_reforco_Vacina=? where Cpd_Vacina=?";
        PreparedStatement pst;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            pst = connection.prepareStatement(query);
            pst.setString(1, vacina.getNomeVacina());
            pst.setString(2, vacina.getData_reforco());
            pst.setInt(3, vacina.getCodVacina());
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar Vacina");
        }
    }

    @Override
    public void deleteVacina(Vacina vacina) {
        String query = "delete from vacina where Cod_vacina =?";
        PreparedStatement pst;
        Connection connection;

        try {
            connection = ConnectionFactory.concectBD();
            pst = connection.prepareStatement(query);
            pst.setInt(1, (vacina.getCodVacina()));
            pst.execute();
            pst.close();
            connection.close();
            System.out.println(vacina.getCodVacina());

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Erro ao deletar Vacina");
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
