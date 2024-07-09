package modelo;

import java.io.Serializable;

public class Remedio implements Serializable{

  private static int codRemedio=1;
  private String nome;
  private String descricao;

  public Remedio() {
  }

  public Remedio(String nome, String descricao) {
    this.nome = nome;
    this.descricao = descricao;
    codRemedio++;
  }

  public static void setCodRemedio(int codRemedio) {
    Remedio.codRemedio = codRemedio;
  }
  
  public static int getCodRemedio() {
    return codRemedio;
  }
  
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  } 
  
}
