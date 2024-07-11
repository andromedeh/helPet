package data.JBDC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.ConnectionFactory;
import data.DAO.IConsultaDao;
import modelo.Consulta;

public class ConsultaDaoJdbc implements IConsultaDao {
    private Connection connection;

    @Override
    public List<Consulta> getAllConsulta() {
        List<Consulta> consultas = null;
        String query = "SELECT * FROM consulta";
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                consultas = new ArrayList<>();
                while (resultSet.next()) {
                    Consulta c = new Consulta();
                    c.setNomePet(resultSet.getString("nomePet_Consulta"));
                    c.setCpfDono(resultSet.getLong("cpfDono_Consulta"));
                    c.setCrmvMedico(resultSet.getInt("crmvMedico_Consulta"));
                    c.setHorario(resultSet.getString("horario_Consulta"));
                    c.setDate(resultSet.getDate("date_Consulta"));
                    consultas.add(c);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultas;
    }

    @Override
    public void createConsulta(Consulta consulta) {
        String query = "INSERT INTO consulta (nomePet_Consulta, cpfDono_Consulta, crmvMedico_Consulta, horario_Consulta, date_Consulta) VALUES (?, ?, ?, ?, ?)";
        try {
            System.out.println(consulta.getNomePet());
            System.out.println(consulta.getCpfDono());
            connection = ConnectionFactory.concectBD();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, consulta.getNomePet());
            preparedStatement.setLong(2, consulta.getCpfDono());
            preparedStatement.setInt(3, consulta.getCrmvMedico());
            preparedStatement.setString(4, consulta.getHorario());
            preparedStatement.setDate(5, consulta.getDate());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir Consulta");
        }
    }

    @Override
    public Consulta readConsulta(String nomePet, long cpfDono, int crmvMedico, Date data) {
        String query = "SELECT * FROM consulta WHERE nomePet_Consulta = ? AND cpfDono_Consulta = ? AND crmvMedico_Consulta = ? AND date_Consulta = ?";
        Consulta consulta = null;
        try {
            connection = ConnectionFactory.concectBD();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nomePet);
            preparedStatement.setLong(2, cpfDono);
            preparedStatement.setInt(3, crmvMedico);
            preparedStatement.setDate(4, data);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                consulta = new Consulta();
                consulta.setNomePet(resultSet.getString("nomePet_Consulta"));
                consulta.setCpfDono(resultSet.getLong("cpfDono_Consulta"));
                consulta.setCrmvMedico(resultSet.getInt("crmvMedico_Consulta"));
                consulta.setHorario(resultSet.getString("horario_Consulta"));
                consulta.setDate(resultSet.getDate("date_Consulta"));
            }
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao listar Consulta");
        }
        return consulta;
    }

    @Override
    public void updateConsulta(Consulta consulta) {
        String query = "UPDATE consulta SET horario_Consulta = ? WHERE nomePet_Consulta = ? AND cpfDono_Consulta = ? AND crmvMedico_Consulta = ? and date_Consulta=?";
        try {
            connection = ConnectionFactory.concectBD();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, consulta.getHorario());
            preparedStatement.setString(2, consulta.getNomePet());
            preparedStatement.setLong(3, consulta.getCpfDono());
            preparedStatement.setInt(4, consulta.getCrmvMedico());  
            preparedStatement.setDate(5, consulta.getDate());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar Consulta");
        }
    }

    @Override
    public void deleteConsulta(Consulta consulta) {
        String query = "DELETE FROM consulta WHERE nomePet_Consulta = ? AND cpfDono_Consulta = ? AND crmvMedico_Consulta = ? and date_Consulta=?";
        try {
            connection = ConnectionFactory.concectBD();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, consulta.getNomePet());
            preparedStatement.setLong(2, consulta.getCpfDono());
            preparedStatement.setInt(3, consulta.getCrmvMedico());
            preparedStatement.setDate(4, consulta.getDate());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar Consulta");
        }
    }

    private ResultSet statementExQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    @Override
    public List<Consulta> getAllConsultaMedico(int crmv) {
        List<Consulta> consultas = null;
        String query = "SELECT * FROM consulta where crmvMedico_Consulta="+crmv;
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                consultas = new ArrayList<>();
                while (resultSet.next()) {
                    Consulta c = new Consulta();
                    c.setNomePet(resultSet.getString("nomePet_Consulta"));
                    c.setCpfDono(resultSet.getLong("cpfDono_Consulta"));
                    c.setCrmvMedico(resultSet.getInt("crmvMedico_Consulta"));
                    c.setHorario(resultSet.getString("horario_Consulta"));
                    c.setDate(resultSet.getDate("date_Consulta"));
                    consultas.add(c);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultas;
      }

    @Override
    public List<Consulta> getAllConsultaCliente(long cpf) {
        List<Consulta> consultas = null;
        String query = "SELECT * FROM consulta where cpfDono_Consulta = "+cpf;
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                consultas = new ArrayList<>();
                while (resultSet.next()) {
                    Consulta c = new Consulta();
                    c.setNomePet(resultSet.getString("nomePet_Consulta"));
                    c.setCpfDono(resultSet.getLong("cpfDono_Consulta"));
                    c.setCrmvMedico(resultSet.getInt("crmvMedico_Consulta"));
                    c.setHorario(resultSet.getString("horario_Consulta"));
                    c.setDate(resultSet.getDate("date_Consulta"));
                    consultas.add(c);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultas;
        }

}

