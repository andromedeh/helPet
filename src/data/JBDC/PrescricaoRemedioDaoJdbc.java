package data.JBDC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.ConnectionFactory;
import data.DAO.IPrescricaoRemedioDAO;
import modelo.PrescricaoRemedio;

public class PrescricaoRemedioDaoJdbc implements IPrescricaoRemedioDAO {
    private Connection connection;

    @Override
    public List<PrescricaoRemedio> getAllPrescricaoRemedio() {
        List<PrescricaoRemedio> PrescricaoRemedios = null;
        String query = "SELECT * FROM PrescricaoRemedio";
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                PrescricaoRemedios = new ArrayList<>();
                while (resultSet.next()) {
                    PrescricaoRemedio r = new PrescricaoRemedio();
                    r.setNomePet(resultSet.getString("nomePet_PrescricaoRemedio"));
                    r.setCpfDono(resultSet.getLong("cpfDono_PrescricaoRemedio"));
                    r.setCrmvMedico(resultSet.getInt("crmvMedico_PrescricaoRemedio"));
                    r.setCodigoRemedio(resultSet.getInt("codigoRemedio_PrescricaoRemedio"));
                    PrescricaoRemedios.add(r);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PrescricaoRemedios;
    }

    @Override
    public void createPrescricaoRemedio(PrescricaoRemedio PrescricaoRemedio) {
        String query = "INSERT INTO PrescricaoRemedio (nomePet_PrescricaoRemedio, cpfDono_PrescricaoRemedio, crmvMedico_PrescricaoRemedio, codigoRemedio_PrescricaoRemedio) VALUES (?, ?, ?, ?)";
        try {
            connection = ConnectionFactory.concectBD();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, PrescricaoRemedio.getNomePet());
            preparedStatement.setLong(2, PrescricaoRemedio.getCpfDono());
            preparedStatement.setInt(3, PrescricaoRemedio.getCrmvMedico());
            preparedStatement.setInt(4, PrescricaoRemedio.getCodigoRemedio());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir PrescricaoRemedio");
        }
    }

    @Override
    public PrescricaoRemedio readPrescricaoRemedio(String nomePet, long cpfDono, int crmvMedico, int codigoRemedio) {
        String query = "SELECT * FROM PrescricaoRemedio WHERE nomePet_PrescricaoRemedio = '" + nomePet + "' AND cpfDono_PrescricaoRemedio = " + cpfDono + " AND crmvMedico_PrescricaoRemedio = " + crmvMedico + " AND codigoRemedio_PrescricaoRemedio = " + codigoRemedio;
        PrescricaoRemedio PrescricaoRemedio = null;
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet.next()) {
                PrescricaoRemedio = new PrescricaoRemedio();
                PrescricaoRemedio.setNomePet(resultSet.getString("nomePet_PrescricaoRemedio"));
                PrescricaoRemedio.setCpfDono(resultSet.getLong("cpfDono_PrescricaoRemedio"));
                PrescricaoRemedio.setCrmvMedico(resultSet.getInt("crmvMedico_PrescricaoRemedio"));
                PrescricaoRemedio.setCodigoRemedio(resultSet.getInt("codigoRemedio_PrescricaoRemedio"));
            }
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao listar PrescricaoRemedio");
        }
        return PrescricaoRemedio;
    }

    @Override
    public void updatePrescricaoRemedio(PrescricaoRemedio PrescricaoRemedio) {
        String query = "UPDATE PrescricaoRemedio SET codigoRemedio_PrescricaoRemedio = ? WHERE nomePet_PrescricaoRemedio = ? AND cpfDono_PrescricaoRemedio = ? AND crmvMedico_PrescricaoRemedio = ?";
        try {
            connection = ConnectionFactory.concectBD();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, PrescricaoRemedio.getCodigoRemedio());
            preparedStatement.setString(2, PrescricaoRemedio.getNomePet());
            preparedStatement.setLong(3, PrescricaoRemedio.getCpfDono());
            preparedStatement.setInt(4, PrescricaoRemedio.getCrmvMedico());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar PrescricaoRemedio");
        }
    }

    @Override
    public void deletePrescricaoRemedio(PrescricaoRemedio PrescricaoRemedio) {
        String query = "DELETE FROM PrescricaoRemedio WHERE nomePet_PrescricaoRemedio = ? AND cpfDono_PrescricaoRemedio = ? AND crmvMedico_PrescricaoRemedio = ? AND codigoRemedio_PrescricaoRemedio = ?";
        try {
            connection = ConnectionFactory.concectBD();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, PrescricaoRemedio.getNomePet());
            preparedStatement.setLong(2, PrescricaoRemedio.getCpfDono());
            preparedStatement.setInt(3, PrescricaoRemedio.getCrmvMedico());
            preparedStatement.setInt(4, PrescricaoRemedio.getCodigoRemedio());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar PrescricaoRemedio");
        }
    }

    private ResultSet statementExQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    @Override
    public List<PrescricaoRemedio> getAllPrescricaoRemedioMedico(int crmv) {
        List<PrescricaoRemedio> PrescricaoRemedios = null;
        String query = "SELECT * FROM PrescricaoRemedio WHERE crmvMedico_PrescricaoRemedio = " + crmv;
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                PrescricaoRemedios = new ArrayList<>();
                while (resultSet.next()) {
                    PrescricaoRemedio r = new PrescricaoRemedio();
                    r.setNomePet(resultSet.getString("nomePet_PrescricaoRemedio"));
                    r.setCpfDono(resultSet.getLong("cpfDono_PrescricaoRemedio"));
                    r.setCrmvMedico(resultSet.getInt("crmvMedico_PrescricaoRemedio"));
                    r.setCodigoRemedio(resultSet.getInt("codigoRemedio_PrescricaoRemedio"));
                    PrescricaoRemedios.add(r);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PrescricaoRemedios;
    }

    @Override
    public List<PrescricaoRemedio> getAllPrescricaoRemedioCliente(long cpf) {
        List<PrescricaoRemedio> PrescricaoRemedios = null;
        String query = "SELECT * FROM PrescricaoRemedio WHERE cpfDono_PrescricaoRemedio = " + cpf;
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                PrescricaoRemedios = new ArrayList<>();
                while (resultSet.next()) {
                    PrescricaoRemedio r = new PrescricaoRemedio();
                    r.setNomePet(resultSet.getString("nomePet_PrescricaoRemedio"));
                    r.setCpfDono(resultSet.getLong("cpfDono_PrescricaoRemedio"));
                    r.setCrmvMedico(resultSet.getInt("crmvMedico_PrescricaoRemedio"));
                    r.setCodigoRemedio(resultSet.getInt("codigoRemedio_PrescricaoRemedio"));
                    PrescricaoRemedios.add(r);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PrescricaoRemedios;
    }
    
}
