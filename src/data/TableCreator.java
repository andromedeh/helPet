package data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {
    
    public void createAllTables() {
        try {
            Connection connection =ConnectionFactory.concectBD();
            createTableVacina(connection);
            createTableRemedio(connection);
            createTableExame(connection);
            createTableProfissional(connection);
            createTableCliente(connection);
            createTablePet(connection);
            createTableMedicoVeterinario(connection);
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas");
            e.printStackTrace();
        }
        
    }
    public static void statementExtUpdate(Connection connection, String query) throws SQLException{
        Statement statement;
            statement = connection.createStatement();
            statement.executeUpdate(query);

    }

    //criar tabelas separadas
    public void createTableVacina(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS vacina (" +
                       "Cod_Vacina SERIAL PRIMARY KEY," +
                       "Nome_Vacina VARCHAR(200)," +
                       "Data_aplicacao_vacina DATE," +
                       "Data_reforco_vacina DATE" +
                       ");";
        statementExtUpdate(connection, query);
        System.out.println("Vacina table created");
    }
    
    public void createTableRemedio(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS remedio (" +
                       "Cod_remedio SERIAL PRIMARY KEY," +
                       "Nome_Remedio VARCHAR(200)," +
                       "Descricao_Remedio VARCHAR(400)" +
                       ");";
        statementExtUpdate(connection, query);
        System.out.println("Remedio table created");
    }
    
    public void createTableExame(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS exame (" +
                       "Cod_Exame SERIAL PRIMARY KEY," +
                       "Nome_Exame VARCHAR(200)," +
                       "Descricao_Exame VARCHAR(400)," +
                       "Data_Exame DATE," +
                       "Hora_Exame VARCHAR(5)" +
                       ");";
        statementExtUpdate(connection, query);
        System.out.println("Exame table created");
    }
    public void createTableProfissional(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS profissional (" +
                       "Nome_Profissional VARCHAR(200)," +
                       "CPF_Profissional BIGINT NOT NULL," +
                       "Telefone_Profissional VARCHAR(11)," +
                       "Funcao_Profissional VARCHAR(100)," +
                       "Email_Profissional VARCHAR(200)," +
                       "Senha_Profissional VARCHAR(200)," +
                       "PRIMARY KEY(CPF_Profissional)" +
                       ");";
        statementExtUpdate(connection, query);
        System.out.println("Profissional table created");
    }
    public void createTableCliente(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS cliente (" +
                       "Nome_Cliente VARCHAR(200)," +
                       "Sobrenome_Cliente VARCHAR(200)," +
                       "CPF_Cliente BIGINT NOT NULL," +
                       "Telefone_Cliente VARCHAR(11)," +
                       "Email_Cliente VARCHAR(200)," +
                       "Endereco_Cliente VARCHAR(400)," +
                       "Senha_Cliente VARCHAR(200)," +
                       "PRIMARY KEY(CPF_Cliente)" +
                       ");";
        statementExtUpdate(connection, query);
        System.out.println("Cliente table created");
    }
    public void createTablePet(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS pet (" +
                       "Nome_Pet VARCHAR(200) NOT NULL," +
                       "Raca_Pet VARCHAR(100)," +
                       "Idade_Pet INTEGER," +
                       "Peso_Pet FLOAT," +
                       "Especie_Pet VARCHAR(100)," +
                       "CPF_dono_pet BIGINT NOT NULL," +
                       "PRIMARY KEY(Nome_Pet, CPF_dono_pet)," +
                       "FOREIGN KEY(CPF_dono_pet) REFERENCES cliente(CPF_Cliente)" +
                       ");";
        statementExtUpdate(connection, query);
        System.out.println("Pet table created");
    }
    public void createTableMedicoVeterinario(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS medico_veterinario (" +
                       "Nome_MedicoVeterinario VARCHAR(200)," +
                       "CPF_MedicoVeterinario BIGINT NOT NULL," +
                       "Telefone_MedicoVeterinario BIGINT," +
                       "Funcao_MedicoVeterinario VARCHAR(100)," +
                       "Email_MedicoVeterinario VARCHAR(200)," +
                       "Senha_MedicoVeterinario VARCHAR(200)," +
                       "CRMV_MedicoVeterinario INTEGER PRIMARY KEY" +
                       ");";
        statementExtUpdate(connection, query);
        System.out.println("Medico Veterinario table created");
    } 
}
