package dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import modelo.Cliente;
import modelo.Pet;

public class DadosPet {
  private final String arquivoPet = "Pets.ser";

  public void cadastrarPet(Pet p) throws FileNotFoundException, IOException {
    ArrayList<Pet> pets = (ArrayList<Pet>) listarPet();
    pets.add(p);
    try {
      FileOutputStream fluxo = new FileOutputStream(arquivoPet);
      ObjectOutputStream escreverObj = new ObjectOutputStream(fluxo);
      escreverObj.writeObject(pets);
      escreverObj.close();
    } catch (FileNotFoundException ex) {
      System.out.println(ex.getMessage());
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }

  public ArrayList<Pet> listarPet() throws IOException {
    ArrayList<Pet> pets = new ArrayList<Pet>();
    File arq = new File(arquivoPet);
    if (!arq.exists()) {
      arq.createNewFile();
      return pets;
    }
    FileInputStream fluxo;
    ObjectInputStream lerObj = null;
    try {
      fluxo = new FileInputStream(arq);
      lerObj = new ObjectInputStream(fluxo);
      pets = (ArrayList<Pet>) lerObj.readObject();
      fluxo.close();
    } catch (FileNotFoundException ex) {
      System.out.print(ex.getMessage());
    } catch (ClassNotFoundException | IOException ex) {
      System.out.print(ex.getMessage());
    }
    return pets;
  }

  public Pet pesquisarPetNome(String nome)
      throws IOException, FileNotFoundException, ClassNotFoundException {
    Pet flag = null;
    ArrayList<Pet> pets = listarPet();
    for (Pet pet : pets) {
      if (nome.equals(pet.getNomePet())) {
        flag = pet;
        break;
      }
    }
    return flag;
  }
}
