package modelo;

public class RealizaExame {
    private String nomePet;
    private long cpfDono;
    private int crmvMedico;
    private int codigoExame;

    public RealizaExame(){}
    public RealizaExame(String nomePet, long cpfDono, int crmcMedico,int codigoExame){
        setCodigoExame(codigoExame);
        setCpfDono(cpfDono);
        setCrmvMedico(crmcMedico);
        setNomePet(nomePet);
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public long getCpfDono() {
        return cpfDono;
    }

    public void setCpfDono(long cpfDono) {
        this.cpfDono = cpfDono;
    }

    public int getCrmvMedico() {
        return crmvMedico;
    }

    public void setCrmvMedico(int crmvMedico) {
        this.crmvMedico = crmvMedico;
    }

    public int getCodigoExame() {
        return codigoExame;
    }

    public void setCodigoExame(int codigoExame) {
        this.codigoExame = codigoExame;
    }

    
}
