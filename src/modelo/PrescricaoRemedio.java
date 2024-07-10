package modelo;

public class PrescricaoRemedio {
    private String nomePet;
    private long cpfDono;
    private int crmvMedico;
    private int codigoRemedio;

    public PrescricaoRemedio(){}
    public PrescricaoRemedio(String nomePet, long cpfDono, int crmcMedico,int codigoRemedio){
        setCodigoRemedio(codigoRemedio);
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
    public int getCodigoRemedio() {
        return codigoRemedio;
    }
    public void setCodigoRemedio(int codigoRemedio) {
        this.codigoRemedio = codigoRemedio;
    }

    
}
