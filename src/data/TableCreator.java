package data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {

    public void createAllTables() {
        try {
            Connection connection = ConnectionFactory.concectBD();
            createTableVacina(connection);
            createTableRemedio(connection);
            createTableExame(connection);
            createTableProfissional(connection);
            createTableCliente(connection);
            createTablePet(connection);
            createTableMedicoVeterinario(connection);
            createTableAplicaVacina(connection);
            createTablePrescicaoRemedio(connection);
            createTableRealizaExame(connection);
            createTableConsulta(connection);
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas");
            e.printStackTrace();
        }

    }

    public static void statementExtUpdate(Connection connection, String query) throws SQLException {
        Statement statement;
        statement = connection.createStatement();
        statement.executeUpdate(query);

    }

    // criar tabelas separadas
    public void createTableVacina(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS vacina (" +
                "Cod_Vacina SERIAL PRIMARY KEY," +
                "Nome_Vacina VARCHAR(200)," +
                "Data_reforco_vacina VARCHAR(200)" +
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
                "Descricao_Exame VARCHAR(400)" +
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
                "FOREIGN KEY(CPF_dono_pet) REFERENCES cliente(CPF_Cliente) ON DELETE CASCADE" +
                ");";
        statementExtUpdate(connection, query);
        System.out.println("Pet table created");
    }

    public void createTableMedicoVeterinario(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS medico_veterinario (" +
                "CPF_MedicoVeterinario BIGINT NOT NULL," +
                "CRMV_MedicoVeterinario INTEGER PRIMARY KEY," +
                "FOREIGN KEY(CPF_MedicoVeterinario) REFERENCES profissional(CPF_Profissional) ON DELETE CASCADE" +
                ");";
        statementExtUpdate(connection, query);
        System.out.println("Medico Veterinario table created");
    }

    public void createTableRealizaExame(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS realizaExame (" +
                "nomePet_RealizaExame VARCHAR(200) NOT NULL," +
                "cpfDono_RealizaExame BIGINT NOT NULL," +
                "crmvMedico_RealizaExame INT NOT NULL," +
                "codigoExame_RealizaExame INT," +
                "PRIMARY KEY(nomePet_RealizaExame, cpfDono_RealizaExame, crmvMedico_RealizaExame)," +
                "FOREIGN KEY (nomePet_RealizaExame, cpfDono_RealizaExame) REFERENCES pet(Nome_Pet, CPF_dono_pet)," +
                "FOREIGN KEY (crmvMedico_RealizaExame) REFERENCES medico_veterinario(CRMV_MedicoVeterinario)," +
                "FOREIGN KEY (codigoExame_RealizaExame) REFERENCES exame(Cod_Exame)" +
                ");";
        statementExtUpdate(connection, query);
        System.out.println("RealizaExame table created");
    }

    public void createTablePrescicaoRemedio(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS PrescicaoRemedio (" +
                "nomePet_PrescicaoRemedio VARCHAR(200) NOT NULL," +
                "cpfDono_PrescicaoRemedio BIGINT NOT NULL," +
                "crmvMedico_PrescicaoRemedio INT NOT NULL," +
                "codigoExame_PrescicaoRemedio INT," +
                "PRIMARY KEY(nomePet_PrescicaoRemedio, cpfDono_PrescicaoRemedio, crmvMedico_PrescicaoRemedio)," +
                "FOREIGN KEY (nomePet_PrescicaoRemedio, cpfDono_PrescicaoRemedio) REFERENCES pet(Nome_Pet, CPF_dono_pet),"
                +
                "FOREIGN KEY (crmvMedico_PrescicaoRemedio) REFERENCES medico_veterinario(CRMV_MedicoVeterinario)," +
                "FOREIGN KEY (codigoExame_PrescicaoRemedio) REFERENCES remedio(Cod_remedio)" +
                ");";
        statementExtUpdate(connection, query);
        System.out.println("PrescicaoRemedio table created");
    }

    public void createTableAplicaVacina(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS AplicaVacina (" +
                "nomePet_AplicaVacina VARCHAR(200) NOT NULL," +
                "cpfDono_AplicaVacina BIGINT NOT NULL," +
                "crmvMedico_AplicaVacina INT NOT NULL," +
                "codigoExame_AplicaVacina INT," +
                "Data_aplicacao_vacina_AplicaVacina DATE," +
                "Data_reforco_vacina_AplicaVacina DATE," +
                "PRIMARY KEY(nomePet_AplicaVacina, cpfDono_AplicaVacina, crmvMedico_AplicaVacina)," +
                "FOREIGN KEY (nomePet_AplicaVacina, cpfDono_AplicaVacina) REFERENCES pet(Nome_Pet, CPF_dono_pet)," +
                "FOREIGN KEY (crmvMedico_AplicaVacina) REFERENCES medico_veterinario(CRMV_MedicoVeterinario)," +
                "FOREIGN KEY (codigoExame_AplicaVacina) REFERENCES vacina(Cod_vacina)" +
                ");";
        statementExtUpdate(connection, query);
        System.out.println("AplicaVacina table created");
    }
    

    public void createTableConsulta(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS consulta (" +
                "Nome_Pet VARCHAR(200) NOT NULL," +
                "CPF_dono_pet BIGINT NOT NULL," +
                "CRMV_MedicoVeterinario INTEGER NOT NULL," +
                "Horario TIME," +
                "Data_Consulta DATE NOT NULL," +
                "PRIMARY KEY (Nome_Pet, CPF_dono_pet, CRMV_MedicoVeterinario, Data_Consulta)," +
                "FOREIGN KEY (Nome_Pet, CPF_dono_pet) REFERENCES pet(Nome_Pet, CPF_dono_pet)," +
                "FOREIGN KEY (CRMV_MedicoVeterinario) REFERENCES medico_veterinario(CRMV_MedicoVeterinario)" +
                ");";
        statementExtUpdate(connection, query);
        System.out.println("Consulta table created");
    }
}
