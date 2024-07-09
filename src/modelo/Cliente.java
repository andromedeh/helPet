package modelo;

import java.io.Serializable;

public class Cliente implements Serializable {
  private String nome;
  private String sobrenome;
  private long cpf;
  private long telefone;
  private String email;
  private String endereco;
  private String senha;

  public Cliente(){
    
  }

  public Cliente(String nome, String sobrenome, long cpf, long telefone, String email, String endereco, String senha,
      String confirmarSenha) {
    setNome(nome);
    setSobrenome(sobrenome);
    setCpf(cpf);
    setTelefone(telefone);
    setEmail(email);
    setEndereco(endereco);
    setSenha(senha);
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

}
