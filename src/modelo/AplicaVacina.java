package modelo;

import java.sql.Date;

public class AplicaVacina {
    private String nomePet;
    private long cpfDono;
    private int crmvMedico;
    private int codigoVacina;
    private Date data_aplicacao;
    public AplicaVacina(){}
    public AplicaVacina(String nomePet, long cpfDono, int crmcMedico,int codigoVacina, Date data_aplicacao){
        setCodigoVacina(codigoVacina);
        setCpfDono(cpfDono);
        setCrmvMedico(crmcMedico);
        setNomePet(nomePet);
        setData_aplicacao(data_aplicacao);
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
    public Date getData_aplicacao() {
        return data_aplicacao;
    }
    public void setData_aplicacao(Date data_aplicacao) {
        this.data_aplicacao = data_aplicacao;
    }
    
    
}
