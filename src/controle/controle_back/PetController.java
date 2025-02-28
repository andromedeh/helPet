package controle.controle_back;

import java.util.ArrayList;

import data.DAO.IPetDAO;
import data.JBDC.PetDaoJdbc;
import modelo.Pet;

public class PetController {
    IPetDAO petDAO = new PetDaoJdbc();

    public void cadastrarPet (String nome, String raca, int idade, float peso, String especie, long cpfDono){
        Pet pet = new Pet(nome, raca, idade, peso, especie, cpfDono);
        petDAO.createPet(pet);
    }
    
    public ArrayList<Pet> listarPet(){
        ArrayList<Pet> pets = (ArrayList<Pet>) petDAO.getAllPet();
        return pets;
    }

    public ArrayList<Pet> listarPetDono(long cpf){
      ArrayList<Pet> pets = (ArrayList<Pet>) petDAO.getAllPetDono(cpf);
      return pets;
  }

    public Pet pesquisarPets (long cpfDono, String nome){
      return petDAO.readPet(cpfDono, nome);
    }

    public void atualizarPet (String nome, String raca, int idade, float peso, String especie, long cpfDono){//hanny boba
      Pet pet = new Pet(nome, raca, idade, peso, especie, cpfDono);
      petDAO.updatePet(pet);
    }

    public void deletarPet (String nome, String raca, int idade, float peso, String especie, long cpfDono){
      Pet pet = new Pet(nome, raca, idade, peso, especie, cpfDono);
      petDAO.deletePet(pet);
    }


}
