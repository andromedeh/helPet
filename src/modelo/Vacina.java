package modelo;

import java.io.Serializable;
import java.sql.Date;


public class Vacina implements Serializable{

private String nomeVacina;
private static int codVacina=1;
private Date data;
private String hora;

public Vacina() {
}

public Vacina(String nome, Date data, String hora){
  setNomeVacina(nome);
  setData(data);
  setHora(hora);
  codVacina++;
}

public static int getCodVacina() {
  return codVacina;
}

public static void setCodVacina(int codVacina) {
  Vacina.codVacina = codVacina;
}

public String getNomeVacina() {
  return nomeVacina;
}

public void setNomeVacina(String nomeVacina) {
  this.nomeVacina = nomeVacina;
}

public Date getData() {
  return data;
}

public void setData(Date data) {
  this.data = data;
}

public String getHora() {
  return hora;
}

public void setHora(String hora) {
  this.hora = hora;
}


}
