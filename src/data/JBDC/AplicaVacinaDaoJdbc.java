package data.JBDC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.ConnectionFactory;
import data.DAO.IAplicaVacinaDAO;
import modelo.AplicaVacina;

public class AplicaVacinaDaoJdbc implements IAplicaVacinaDAO {
    private Connection connection;

    @Override
    public List<AplicaVacina> getAllAplicaVacina() {
        List<AplicaVacina> AplicaVacinas = null;
        String query = "SELECT * FROM AplicaVacina";
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                AplicaVacinas = new ArrayList<>();
                while (resultSet.next()) {
                    AplicaVacina r = new AplicaVacina();
                    r.setNomePet(resultSet.getString("nomePet_AplicaVacina"));
                    r.setCpfDono(resultSet.getLong("cpfDono_AplicaVacina"));
                    r.setCrmvMedico(resultSet.getInt("crmvMedico_AplicaVacina"));
                    r.setCodigoVacina(resultSet.getInt("codigoVacina_AplicaVacina"));
                    r.setData_aplicacao(resultSet.getDate("Data_aplicacao_AplicaVacina"));
                    AplicaVacinas.add(r);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AplicaVacinas;
    }

    @Override
    public void createAplicaVacina(AplicaVacina AplicaVacina) {
        String query = "INSERT INTO AplicaVacina (nomePet_AplicaVacina, cpfDono_AplicaVacina, crmvMedico_AplicaVacina, codigoVacina_AplicaVacina, Data_aplicacao_AplicaVacina VALUES (?, ?, ?, ?,?)";
        try {
            connection = ConnectionFactory.concectBD();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, AplicaVacina.getNomePet());
            preparedStatement.setLong(2, AplicaVacina.getCpfDono());
            preparedStatement.setInt(3, AplicaVacina.getCrmvMedico());
            preparedStatement.setInt(4, AplicaVacina.getCodigoVacina());
            preparedStatement.setDate(5, AplicaVacina.getData_aplicacao());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir AplicaVacina");
        }
    }

    @Override
    public AplicaVacina readAplicaVacina(String nomePet, long cpfDono, int crmvMedico, int codigoVacina) {
        String query = "SELECT * FROM AplicaVacina WHERE nomePet_AplicaVacina = '" + nomePet + "' AND cpfDono_AplicaVacina = " + cpfDono + " AND crmvMedico_AplicaVacina = " + crmvMedico + " AND codigoVacina_AplicaVacina = " + codigoVacina;
        AplicaVacina AplicaVacina = null;
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet.next()) {
                AplicaVacina = new AplicaVacina();
                AplicaVacina.setNomePet(resultSet.getString("nomePet_AplicaVacina"));
                AplicaVacina.setCpfDono(resultSet.getLong("cpfDono_AplicaVacina"));
                AplicaVacina.setCrmvMedico(resultSet.getInt("crmvMedico_AplicaVacina"));
                AplicaVacina.setCodigoVacina(resultSet.getInt("codigoVacina_AplicaVacina"));
                AplicaVacina.setData_aplicacao(resultSet.getDate("Data_aplicacao_AplicaVacina"));
            }
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao listar AplicaVacina");
        }
        return AplicaVacina;
    }

    @Override
    public void updateAplicaVacina(AplicaVacina AplicaVacina) {
        String query = "UPDATE AplicaVacina SET codigoVacina_AplicaVacina = ?, Data_aplicacao_AplicaVacina=? WHERE nomePet_AplicaVacina = ? AND cpfDono_AplicaVacina = ? AND crmvMedico_AplicaVacina = ?";
        try {
            connection = ConnectionFactory.concectBD();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, AplicaVacina.getCodigoVacina());
            preparedStatement.setDate(2, AplicaVacina.getData_aplicacao());
            preparedStatement.setString(3, AplicaVacina.getNomePet());
            preparedStatement.setLong(4, AplicaVacina.getCpfDono());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar AplicaVacina");
        }
    }

    @Override
    public void deleteAplicaVacina(AplicaVacina AplicaVacina) {
        String query = "DELETE FROM AplicaVacina WHERE nomePet_AplicaVacina = ? AND cpfDono_AplicaVacina = ? AND crmvMedico_AplicaVacina = ? AND codigoVacina_AplicaVacina = ? and Data_aplicacao_AplicaVacina=? ";
        try {
            connection = ConnectionFactory.concectBD();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, AplicaVacina.getNomePet());
            preparedStatement.setLong(2, AplicaVacina.getCpfDono());
            preparedStatement.setInt(3, AplicaVacina.getCrmvMedico());
            preparedStatement.setInt(4, AplicaVacina.getCodigoVacina());
            preparedStatement.setDate(5, AplicaVacina.getData_aplicacao());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar AplicaVacina");
        }
    }

    private ResultSet statementExQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    @Override
    public List<AplicaVacina> getAllAplicaVacinaMedico(int crmv) {
        List<AplicaVacina> AplicaVacinas = null;
        String query = "SELECT * FROM AplicaVacina WHERE crmvMedico_AplicaVacina = " + crmv;
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                AplicaVacinas = new ArrayList<>();
                while (resultSet.next()) {
                    AplicaVacina r = new AplicaVacina();
                    r.setNomePet(resultSet.getString("nomePet_AplicaVacina"));
                    r.setCpfDono(resultSet.getLong("cpfDono_AplicaVacina"));
                    r.setCrmvMedico(resultSet.getInt("crmvMedico_AplicaVacina"));
                    r.setCodigoVacina(resultSet.getInt("codigoVacina_AplicaVacina"));
                    r.setData_aplicacao(resultSet.getDate("Data_aplicacao_AplicaVacina"));
                    AplicaVacinas.add(r);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AplicaVacinas;
    }

    @Override
    public List<AplicaVacina> getAllAplicaVacinaCliente(long cpf) {
        List<AplicaVacina> AplicaVacinas = null;
        String query = "SELECT * FROM AplicaVacina WHERE cpfDono_AplicaVacina = " + cpf;
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                AplicaVacinas = new ArrayList<>();
                while (resultSet.next()) {
                    AplicaVacina r = new AplicaVacina();
                    r.setNomePet(resultSet.getString("nomePet_AplicaVacina"));
                    r.setCpfDono(resultSet.getLong("cpfDono_AplicaVacina"));
                    r.setCrmvMedico(resultSet.getInt("crmvMedico_AplicaVacina"));
                    r.setCodigoVacina(resultSet.getInt("codigoVacina_AplicaVacina"));
                    r.setData_aplicacao(resultSet.getDate("Data_aplicacao_AplicaVacina"));
                    AplicaVacinas.add(r);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AplicaVacinas;
    }
    
}
