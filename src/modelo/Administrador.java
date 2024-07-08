package modelo;

public class Administrador {
  private final int codigo;
  private final String senha;

  public Administrador (){
    this.codigo = 123456;
    this.senha = "Adm00000";
  }

  public int getCodigo() {
    return codigo;
  }

  public String getSenha(){
    return senha;
  }
}
