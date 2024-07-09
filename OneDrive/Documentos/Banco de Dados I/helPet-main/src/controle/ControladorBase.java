// Classe abstrata dos controladores, serve para trocar de forma mais eficiente o gerenciador das cenas
package controle;

public abstract class ControladorBase {
    protected GerenciadorCena gerenciador;
    protected static String loginInstanciado;

    public void setGerenciador(GerenciadorCena gerenciador) {
        this.gerenciador = gerenciador;
    }
}
