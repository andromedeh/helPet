package controle;

public abstract class ControladorBase {
    protected GerenciadorCena gerenciador;
    protected static String loginInstanciado;

    public void setGerenciador(GerenciadorCena gerenciador) {
        this.gerenciador = gerenciador;
    }
}