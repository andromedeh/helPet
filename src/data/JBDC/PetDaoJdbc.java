package data.JBDC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.ConnectionFactory;
import data.DAO.IPetDAO;
import modelo.Pet;

public class PetDaoJdbc implements IPetDAO {
    private Connection connection;

    @Override
    public List<Pet> getAllPet() {
        List<Pet> pets = null;
        String query = "select * from pet";
        try {
            connection = ConnectionFactory.concectBD();
            ResultSet resultSet = statementExQuery(connection, query);
            if (resultSet != null) {
                pets = new ArrayList<Pet>();
                while (resultSet.next()) {
                    Pet p = new Pet();
                    p.setNomePet(resultSet.getString("Nome_Pet"));
                    p.setRaca(resultSet.getString("Raca_Pet"));
                    p.setIdade(resultSet.getInt("Idade_Pet"));
                    p.setPeso(resultSet.getFloat("Peso_Pet"));
                    p.setEspecie(resultSet.getString("Especie_Pet"));
                    p.setCpfDono(resultSet.getLong("CPF_dono_pet"));
                    pets.add(p);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pets;
    }

    @Override
    public void createPet(Pet pet) {
        String query = "insert into pet (Nome_Pet, Raca_Pet, Idade_Pet, Peso_Pet, Especie_Pet, CPF_dono_pet) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pet.getNomePet());
            preparedStatement.setString(2, pet.getRaca());
            preparedStatement.setInt(3, pet.getIdade());
            preparedStatement.setFloat(4, pet.getPeso());
            preparedStatement.setString(5, pet.getEspecie());
            preparedStatement.setLong(6, pet.getCpfDono());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir Pet");
        }
    }

    @Override
    public Pet readPet(String nome) {
        String query = "select * from pet where cpf_dono_Pet=?";
        Connection connection = ConnectionFactory.concectBD();
        Pet p = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nome);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                p = new Pet();
                p.setNomePet(resultSet.getString("Nome_Pet"));
                p.setRaca(resultSet.getString("Raca_Pet"));
                p.setIdade(resultSet.getInt("Idade_Pet"));
                p.setPeso(resultSet.getFloat("Peso_Pet"));
                p.setEspecie(resultSet.getString("Especie_Pet"));
                p.setCpfDono(resultSet.getLong("cpf_dono_pet"));
            }
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar Pet");
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public void updatePet(Pet pet) {
        String query = "update pet set Nome_Pet=?, Raca_Pet=?, Idade_Pet=?, Peso_Pet=?, Especie_Pet=? where cpf_dono_Pet=?";
        PreparedStatement pst;
        Connection connection;
        try {
            connection = ConnectionFactory.concectBD();
            pst = connection.prepareStatement(query);
            pst.setString(1, pet.getNomePet());
            pst.setString(2, pet.getRaca());
            pst.setInt(3, pet.getIdade());
            pst.setFloat(4, pet.getPeso());
            pst.setString(5, pet.getEspecie());
            pst.setLong(6, pet.getCpfDono());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar Pet");
        }
    }

    @Override
    public void deletePet(Pet pet) {
        String query = "delete from pet where cpf_dono_Pet =?";
        PreparedStatement pst;
        Connection connection;

        try {
            connection = ConnectionFactory.concectBD();
            pst = connection.prepareStatement(query);
            pst.setString(1, pet.getNomePet());
            pst.execute();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar Pet");
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
