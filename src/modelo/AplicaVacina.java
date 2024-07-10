package modelo;

public class AplicaVacina {
    private String nomePet;
    private long cpfDono;
    private int crmvMedico;
    private int codigoVacina;

    public AplicaVacina(){}
    public AplicaVacina(String nomePet, long cpfDono, int crmcMedico,int codigoVacina){
        setCodigoVacina(codigoVacina);
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
    public int getCodigoVacina() {
        return codigoVacina;
    }
    public void setCodigoVacina(int codigoVacina) {
        this.codigoVacina = codigoVacina;
    }

    
}
