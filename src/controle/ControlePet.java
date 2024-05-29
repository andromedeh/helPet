package controle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import dados.DadosPet;
import modelo.Pet;

public class ControlePet {
  private DadosPet dadosPet = new DadosPet();

  public ArrayList<Pet> getListaPets() throws IOException, FileNotFoundException, ClassNotFoundException {
    return dadosPet.listarPet();
  }

  public void cadastrarPet(String nomePet, String raca, int idade, float peso, String especie)
      throws IOException {
    Pet p = new Pet(nomePet, raca, idade, peso, especie);
    dadosPet.cadastrarPet(p);
  }

  public Pet procurarPet(String nome) throws IOException, FileNotFoundException, ClassNotFoundException {
    Pet p = dadosPet.pesquisarPetNome(nome);
    return p;
  }
}
