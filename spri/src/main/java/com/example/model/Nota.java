package com.example.model;

public class Nota {
    private Aluno aluno;
    private Materia materia;
    private double valorNota;
    private int bimestre;

    public Nota() {
        // Inicialização padrão se necessário
    }

    // Construtor sem Professor
    public Nota(Aluno aluno, Materia materia, double valorNota, int bimestre) {
        this.aluno = aluno;
        this.materia = materia;
        this.valorNota = valorNota;
        this.bimestre = bimestre;
    }

    // Getters e Setters
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public double getValorNota() {
        return valorNota;
    }

    public void setValorNota(double valorNota) {
        this.valorNota = valorNota;
    }

    public int getBimestre() {
        return bimestre;
    }

    public void setBimestre(int bimestre) {
        this.bimestre = bimestre;
    }

    // toString
    @Override
    public String toString() {
        return "Nota{" +
                "aluno=" + aluno +
                ", materia=" + materia +
                ", valorNota=" + valorNota +
                ", bimestre=" + bimestre +
                '}';
    }
}
