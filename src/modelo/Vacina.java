package modelo;

import java.io.Serializable;
public class Vacina implements Serializable {

  private String nomeVacina;
  private int codVacina;
  private String data_reforco;

  public Vacina() {
  }

  public Vacina(String nome, String data_reforco) {
    setNomeVacina(nome);
    setData_reforco(data_reforco);

  }

  public int getCodVacina() {
    return codVacina;
  }

  public void setCodVacina(int codVacina) {
    this.codVacina = codVacina;
  }

  public String getNomeVacina() {
    return nomeVacina;
  }

  public void setNomeVacina(String nomeVacina) {
    this.nomeVacina = nomeVacina;
  }

  public String getData_reforco() {
    return data_reforco;
  }

  public void setData_reforco(String data_reforco) {
    this.data_reforco = data_reforco;
  }

}
