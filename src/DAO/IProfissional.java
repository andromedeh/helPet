package DAO;

import java.util.List;

import modelo.Profissional;

public interface IProfissional {
    public List<Profissional> getAllProfissional();
    public void createProfissional (Profissional profissional);
    public Profissional readProfissional (long cpf);
    public void updateProfissional (Profissional profissional);
    public void deleteProfissional (Profissional profissional);
}
