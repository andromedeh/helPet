package data.JBDC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.ConnectionFactory;
import data.DAO.IRealizaExameDAO;
import modelo.RealizaExame;

public class RealizaExameDaoJdbc implements IRealizaExameDAO {
    private Connection connection;

    @Override
    public List<RealizaExame> getAllRealizaExame() {
        List<RealizaExame> realizaExames = null;
        String query = "SELECT * FROM realizaExame";
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                realizaExames = new ArrayList<>();
                while (resultSet.next()) {
                    RealizaExame r = new RealizaExame();
                    r.setNomePet(resultSet.getString("nomePet_RealizaExame"));
                    r.setCpfDono(resultSet.getLong("cpfDono_RealizaExame"));
                    r.setCrmvMedico(resultSet.getInt("crmvMedico_RealizaExame"));
                    r.setCodigoExame(resultSet.getInt("codigoExame_RealizaExame"));
                    realizaExames.add(r);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return realizaExames;
    }

    @Override
    public void createRealizaExame(RealizaExame realizaExame) {
        String query = "INSERT INTO realizaExame (nomePet_RealizaExame, cpfDono_RealizaExame, crmvMedico_RealizaExame, codigoExame_RealizaExame) VALUES (?, ?, ?, ?)";
        try {
            connection = ConnectionFactory.concectBD();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, realizaExame.getNomePet());
            preparedStatement.setLong(2, realizaExame.getCpfDono());
            preparedStatement.setInt(3, realizaExame.getCrmvMedico());
            preparedStatement.setInt(4, realizaExame.getCodigoExame());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir RealizaExame");
        }
    }

    @Override
    public RealizaExame readRealizaExame(String nomePet, long cpfDono, int crmvMedico, int codigoExame) {
        String query = "SELECT * FROM realizaExame WHERE nomePet_RealizaExame = '" + nomePet + "' AND cpfDono_RealizaExame = " + cpfDono + " AND crmvMedico_RealizaExame = " + crmvMedico + " AND codigoExame_RealizaExame = " + codigoExame;
        RealizaExame realizaExame = null;
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet.next()) {
                realizaExame = new RealizaExame();
                realizaExame.setNomePet(resultSet.getString("nomePet_RealizaExame"));
                realizaExame.setCpfDono(resultSet.getLong("cpfDono_RealizaExame"));
                realizaExame.setCrmvMedico(resultSet.getInt("crmvMedico_RealizaExame"));
                realizaExame.setCodigoExame(resultSet.getInt("codigoExame_RealizaExame"));
            }
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao listar RealizaExame");
        }
        return realizaExame;
    }

    @Override
    public void updateRealizaExame(RealizaExame realizaExame) {
        String query = "UPDATE realizaExame SET codigoExame_RealizaExame = ? WHERE nomePet_RealizaExame = ? AND cpfDono_RealizaExame = ? AND crmvMedico_RealizaExame = ?";
        try {
            connection = ConnectionFactory.concectBD();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, realizaExame.getCodigoExame());
            preparedStatement.setString(2, realizaExame.getNomePet());
            preparedStatement.setLong(3, realizaExame.getCpfDono());
            preparedStatement.setInt(4, realizaExame.getCrmvMedico());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar RealizaExame");
        }
    }

    @Override
    public void deleteRealizaExame(RealizaExame realizaExame) {
        String query = "DELETE FROM realizaExame WHERE nomePet_RealizaExame = ? AND cpfDono_RealizaExame = ? AND crmvMedico_RealizaExame = ? AND codigoExame_RealizaExame = ?";
        try {
            connection = ConnectionFactory.concectBD();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, realizaExame.getNomePet());
            preparedStatement.setLong(2, realizaExame.getCpfDono());
            preparedStatement.setInt(3, realizaExame.getCrmvMedico());
            preparedStatement.setInt(4, realizaExame.getCodigoExame());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar RealizaExame");
        }
    }

    private ResultSet statementExQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    @Override
    public List<RealizaExame> getAllRealizaExameMedico(int crmv) {
        List<RealizaExame> realizaExames = null;
        String query = "SELECT * FROM realizaExame WHERE crmvMedico_RealizaExame = " + crmv;
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                realizaExames = new ArrayList<>();
                while (resultSet.next()) {
                    RealizaExame r = new RealizaExame();
                    r.setNomePet(resultSet.getString("nomePet_RealizaExame"));
                    r.setCpfDono(resultSet.getLong("cpfDono_RealizaExame"));
                    r.setCrmvMedico(resultSet.getInt("crmvMedico_RealizaExame"));
                    r.setCodigoExame(resultSet.getInt("codigoExame_RealizaExame"));
                    realizaExames.add(r);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return realizaExames;
    }

    @Override
    public List<RealizaExame> getAllRealizaExameCliente(long cpf) {
        List<RealizaExame> realizaExames = null;
        String query = "SELECT * FROM realizaExame WHERE cpfDono_RealizaExame = " + cpf;
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                realizaExames = new ArrayList<>();
                while (resultSet.next()) {
                    RealizaExame r = new RealizaExame();
                    r.setNomePet(resultSet.getString("nomePet_RealizaExame"));
                    r.setCpfDono(resultSet.getLong("cpfDono_RealizaExame"));
                    r.setCrmvMedico(resultSet.getInt("crmvMedico_RealizaExame"));
                    r.setCodigoExame(resultSet.getInt("codigoExame_RealizaExame"));
                    realizaExames.add(r);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return realizaExames;
    }
}
