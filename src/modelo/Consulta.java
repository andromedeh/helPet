package modelo;

import java.sql.Date;

import controle.controle_back.ClienteController;
import controle.controle_back.MedicoVeterinarioController;
import controle.controle_back.ProfissionalController;

public class Consulta {
    private String nomePet;
    private long cpfDono;
    private int crmvMedico;
    private String horario;
    private Date date;

    public Consulta(){}
    public Consulta(String nomePet,long cpfDono, int crmvMedico, String horario, Date date){
        ClienteController cc = new ClienteController();
        MedicoVeterinarioController mvc = new MedicoVeterinarioController();
        ProfissionalController pp = new ProfissionalController();
        setCpfDono(cpfDono);
        setCrmvMedico(crmvMedico);
        setDate(date);  
        setHorario(horario);
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
    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
