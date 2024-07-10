package data.DAO;

import java.util.List;

import modelo.Pet;

public interface IPetDAO {
    public List<Pet> getAllPet();
    public List<Pet> getAllPetDono(long cpf);
    public void createPet(Pet pet);
    public Pet readPet(long cpfDono, String nome);
    public void updatePet(Pet pet);
    public void deletePet(Pet pet);
    
}
