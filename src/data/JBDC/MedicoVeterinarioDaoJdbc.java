package data.JBDC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.ConnectionFactory;
import data.DAO.IMedicoVeterinarioDAO;
import modelo.MedicoVeterinario;

public class MedicoVeterinarioDaoJdbc implements IMedicoVeterinarioDAO {
    

    @Override
    public List<MedicoVeterinario> getAllMedicoVeterinario() {
        List<MedicoVeterinario> medicos = new ArrayList<>();
        String query = "SELECT * FROM medico_veterinario";

        try (Connection connection = ConnectionFactory.concectBD();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                MedicoVeterinario m = new MedicoVeterinario();
                m.setCpf(resultSet.getLong("CPF_MedicoVeterinario"));
                m.setCrmv(resultSet.getInt("CRMV_MedicoVeterinario"));
                medicos.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicos;
    }

    @Override
    public void createMedicoVeterinario(MedicoVeterinario medico) {
        String query = "INSERT INTO medico_veterinario (CPF_MedicoVeterinario, CRMV_MedicoVeterinario) VALUES (?, ?)";

        try (Connection connection = ConnectionFactory.concectBD();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, medico.getCpf());
            preparedStatement.setInt(2, medico.getCrmv());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir Médico Veterinário");
        }
    }

    @Override
    public MedicoVeterinario readMedicoVeterinario(int crmv) {
        String query = "SELECT * FROM medico_veterinario WHERE CRMV_MedicoVeterinario = ?";
        MedicoVeterinario medico = null;

        try (Connection connection = ConnectionFactory.concectBD();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, crmv);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    medico = new MedicoVeterinario();
                    medico.setCpf(resultSet.getLong("CPF_MedicoVeterinario"));
                    medico.setCrmv(resultSet.getInt("CRMV_MedicoVeterinario"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar Médico Veterinário");
        }

        return medico;
    }

    @Override
    public void updateMedicoVeterinario(MedicoVeterinario medico) {
        String query = "UPDATE medico_veterinario SET CPF_MedicoVeterinario = ? WHERE CRMV_MedicoVeterinario = ?";

        try (Connection connection = ConnectionFactory.concectBD();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, medico.getCpf());
            preparedStatement.setInt(2, medico.getCrmv());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar Médico Veterinário");
        }
    }

    @Override
    public void deleteMedicoVeterinario(MedicoVeterinario medico) {
        String query = "DELETE FROM medico_veterinario WHERE CRMV_MedicoVeterinario = ?";

        try (Connection connection = ConnectionFactory.concectBD();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, medico.getCrmv());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar Médico Veterinário");
        }
    }

    @Override
    public void deleteMedicoVeterinario(long cpf) {
        String query = "DELETE FROM medico_veterinario WHERE CPF_MedicoVeterinario = ?";

        try (Connection connection = ConnectionFactory.concectBD();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, cpf);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar Médico Veterinário");
        }
    }
}
