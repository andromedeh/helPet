package modelo;

import java.io.Serializable;

public class Pet implements Serializable {
  private String nomePet;
  private String raca;
  private int idade;
  private float peso;
  private String especie;

  public Pet(String nomePet, String raca, int idade, float peso, String especie) {
    setNomePet(nomePet);
    setRaca(raca);
    setIdade(idade);
    setPeso(peso);
    setEspecie(especie);
  }

  public String getNomePet() {
    return nomePet;
  }

  public void setNomePet(String nomePet) {
    this.nomePet = nomePet;
  }

  public String getRaca() {
    return raca;
  }

  public void setRaca(String raca) {
    this.raca = raca;
  }

  public int getIdade() {
    return idade;
  }

  public void setIdade(int idade) {
    this.idade = idade;
  }

  public float getPeso() {
    return peso;
  }

  public void setPeso(float peso) {
    this.peso = peso;
  }

  public String getEspecie() {
    return especie;
  }

  public void setEspecie(String especie) {
    this.especie = especie;
  }
}
