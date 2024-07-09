package data.DAO;

import java.util.List;

import modelo.Pet;

public interface IPetDAO {
    public List<Pet> getAllPet();
    public void createPet(Pet pet);
    public Pet readPet(String nome);
    public void updatePet(Pet pet);
    public void deletePet(Pet pet);
    
}
