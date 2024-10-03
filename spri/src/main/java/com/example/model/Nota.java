package com.example.model;

public class Nota {
    private Aluno aluno;
    private Materia materia;
    private Professor professor;
    private double valorNota;
    private int bimestre;

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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
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
                ", professor=" + professor +
                ", valorNota=" + valorNota +
                ", bimestre=" + bimestre +
                '}';
    }
}
