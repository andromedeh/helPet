package modelo;

public class Administrador {
  private final String email;
  private final String senha;

  public Administrador() {
    this.email = "Adm@gmail.com";
    this.senha = "Adm1234";
  }

  public String getEmail() {
    return email;
  }

  public String getSenha() {
    return senha;
  }
}
