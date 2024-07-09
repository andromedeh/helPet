package modelo;

import java.io.Serializable;
import java.sql.Date;


public class Exame implements Serializable{
  private int codExame;
  private String nomeExame;
  private String descricao;
  private Date data;
  private String hora;

  

    public Exame() {
  }

    public Exame (String nome, String descricao, Date data, String hora){
      setNomeExame(nome);
      setDescricao(descricao);
      setData(data);
      setHora(hora);
    }

  public int getCodExame(){
    return codExame;
  }

  public void setCodExame(int codExame){
    this.codExame = codExame;
  }
  
  public String getNomeExame() {
    return nomeExame;
  }

  public void setNomeExame(String nomeExame) {
    this.nomeExame = nomeExame;
  }
  
  public String getDescricao() {
  return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
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
