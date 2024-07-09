package modelo;

import java.io.Serializable;
import java.sql.Date;


public class Vacina implements Serializable{

private String nomeVacina;
private int codVacina;
private Date data_aplicacao;
private Date data_reforco;

public Vacina() {
}

public Vacina(String nome, Date data_aplicacao, Date data_reforco){
  setNomeVacina(nome);
  setData_aplicacao(data_aplicacao);
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

public Date getData_aplicacao() {
  return data_aplicacao;
}

public void setData_aplicacao(Date data_aplicacao) {
  this.data_aplicacao = data_aplicacao;
}

public Date getData_reforco() {
  return data_reforco;
}

public void setData_reforco(Date data_reforco) {
  this.data_reforco = data_reforco;
}



}
