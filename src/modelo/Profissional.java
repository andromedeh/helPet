package modelo;

import java.io.Serializable;

public class Profissional implements Serializable {
  private String nome;
  private long cpf;
  private long telefone;
  private String funcao;
  private String email;
  private String senha;

  public Profissional() {
  }

  public Profissional(String nome, long cpf, long telefone, String funcao, String email, String senha) {
    setNome(nome);
    setCpf(cpf);
    setTelefone(telefone);
    setFuncao(funcao);
    setEmail(email);
    setSenha(senha);
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public long getCpf() {
    return cpf;
  }

  public void setCpf(long cpf) {
    this.cpf = cpf;
  }

  public long getTelefone() {
    return telefone;
  }

  public void setTelefone(long telefone) {
    this.telefone = telefone;
  }

  public String getFuncao() {
    return funcao;
  }

  public void setFuncao(String funcao) {
    this.funcao = funcao;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

}
