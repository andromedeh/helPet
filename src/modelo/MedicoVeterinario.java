package modelo;

public class MedicoVeterinario extends Profissional {
  private int crmv;

  public MedicoVeterinario(){
    
  }

  public MedicoVeterinario(String nome, long cpf, long telefone, String funcao, String email, String senha,
      String confirmarSenha, int crmv) {
    super(nome, cpf, telefone, funcao, email, senha, confirmarSenha);
    setCrmv(crmv);
  }

  public int getCrmv() {
    return crmv;
  }

  public void setCrmv(int crmv) {
    this.crmv = crmv;
  }

}