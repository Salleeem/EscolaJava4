package com.example.model;

public class Aluno extends Pessoa {
    private int id;
    private String anoEscolar;
    private String turno;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnoEscolar() {
        return anoEscolar;
    }

    public void setAnoEscolar(String anoEscolar) {
        this.anoEscolar = anoEscolar;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    // toString
    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", anoEscolar='" + anoEscolar + '\'' +
                ", turno='" + turno + '\'' +
                "} " + super.toString();
    }
}
