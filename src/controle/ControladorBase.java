package controle;

public abstract class ControladorBase {
    protected GerenciadorCena gerenciador;
    
    public void setGerenciador(GerenciadorCena gerenciador) {
        this.gerenciador = gerenciador;
    }
 
}