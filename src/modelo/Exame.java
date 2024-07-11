package modelo;

import java.io.Serializable;

public class Exame implements Serializable {
  private int codExame;
  private String nomeExame;
  private String descricao;

  public Exame() {
  }

  public Exame(String nome, String descricao) {
    setNomeExame(nome);
    setDescricao(descricao);
  }

  public int getCodExame() {
    return codExame;
  }

  public void setCodExame(int codExame) {
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
}
