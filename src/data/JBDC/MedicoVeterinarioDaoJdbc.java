package data.JBDC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.ConnectionFactory;
import data.DAO.IMedicoVeterinarioDAO;
import modelo.MedicoVeterinario;

public class MedicoVeterinarioDaoJdbc implements IMedicoVeterinarioDAO {
    private Connection connection;

    @Override
    public List<MedicoVeterinario> getAllMedicoVeterinario() {
        List<MedicoVeterinario> medicos = null;
        String query = "select * from medico_veterinario";
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                medicos = new ArrayList<MedicoVeterinario>();
                while (resultSet.next()) {
                    MedicoVeterinario m = new MedicoVeterinario();
                    m.setNome(resultSet.getString("Nome_MedicoVeterinario"));
                    m.setCpf(resultSet.getLong("CPF_MedicoVeterinario"));
                    m.setTelefone(resultSet.getLong("Telefone_MedicoVeterinario"));
                    m.setFuncao(resultSet.getString("Funcao_MedicoVeterinario"));
                    m.setEmail(resultSet.getString("Email_MedicoVeterinario"));
                    m.setSenha(resultSet.getString("Senha_MedicoVeterinario"));
                    m.setCrmv(resultSet.getInt("CRMV_MedicoVeterinario"));
                    medicos.add(m);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicos;
    }

    @Override
    public void createMedicoVeterinario(MedicoVeterinario medico) {
        String query = "insert into medico_veterinario (Nome_MedicoVeterinario, CPF_MedicoVeterinario, Telefone_MedicoVeterinario, Funcao_MedicoVeterinario, Email_MedicoVeterinario, Senha_MedicoVeterinario, CRMV_MedicoVeterinario) values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, medico.getNome());
            preparedStatement.setLong(2, medico.getCpf());
            preparedStatement.setLong(3, medico.getTelefone());
            preparedStatement.setString(4, medico.getFuncao());
            preparedStatement.setString(5, medico.getEmail());
            preparedStatement.setString(6, medico.getSenha());
            preparedStatement.setInt(7, medico.getCrmv());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir Médico Veterinário");
        }
    }

    @Override
    public MedicoVeterinario readMedicoVeterinario(int crmv) {
        String query = "select * from medico_veterinario where CRMV_MedicoVeterinario=?";
        Connection connection = ConnectionFactory.concectBD();
        MedicoVeterinario m = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, crmv);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                m = new MedicoVeterinario();
                m.setNome(resultSet.getString("Nome_MedicoVeterinario"));
                m.setCpf(resultSet.getLong("CPF_MedicoVeterinario"));
                m.setTelefone(resultSet.getLong("Telefone_MedicoVeterinario"));
                m.setFuncao(resultSet.getString("Funcao_MedicoVeterinario"));
                m.setEmail(resultSet.getString("Email_MedicoVeterinario"));
                m.setSenha(resultSet.getString("Senha_MedicoVeterinario"));
                m.setCrmv(resultSet.getInt("CRMV_MedicoVeterinario"));
            }
            resultSet.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar Médico Veterinário");
            ex.printStackTrace();
        }
        return m;
    }

    @Override
    public void updateMedicoVeterinario(MedicoVeterinario medico) {
        String query = "update medico_veterinario set Nome_MedicoVeterinario=?, Telefone_MedicoVeterinario=?, Funcao_MedicoVeterinario=?, Email_MedicoVeterinario=?, Senha_MedicoVeterinario=?, Cpf_MedicoVeterinario=? where CRMV_MedicoVeterinario=?";
        PreparedStatement pst;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            pst = connection.prepareStatement(query);
            pst.setString(1, medico.getNome());
            pst.setLong(2, medico.getTelefone());
            pst.setString(3, medico.getFuncao());
            pst.setString(4, medico.getEmail());
            pst.setString(5, medico.getSenha());
            pst.setInt(6, medico.getCrmv());
            pst.setLong(7, medico.getCpf());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar Médico Veterinário");
        }
    }

    @Override
    public void deleteMedicoVeterinario(MedicoVeterinario medico) {
        String query = "delete from medico_veterinario where CRMV_MedicoVeterinario=?";
        PreparedStatement pst;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            pst = connection.prepareStatement(query);
            pst.setLong(1, medico.getCrmv());
            pst.execute();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar Médico Veterinário");
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
