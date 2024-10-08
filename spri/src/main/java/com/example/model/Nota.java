package com.example.model;

import java.util.Arrays;

public class Nota {

    //Atributos das Notas
    private Aluno aluno; //Chave Estrangeira de Aluno
    private Materia materia; //Chave Estrangeira de Materia
    private double[] notasBimestre; // Como são 5 notas para cada bimestre usamos uma array
    private int bimestre;
    private int faltas;
    
    public Nota() {
        notasBimestre = new double[5]; // Aqui atribuimos que serão 5 notas
    }

    public Nota(Aluno aluno, Materia materia, double[] notasBimestre, int bimestre, int faltas) {
        this.aluno = aluno;
        this.materia = materia;
        this.notasBimestre = notasBimestre;
        this.bimestre = bimestre;
        this.faltas = faltas;
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

    public double[] getNotasBimestre() {
        return notasBimestre;
    }

    public void setNotasBimestre(double[] notasBimestre) {
        this.notasBimestre = notasBimestre;
    }

    public int getBimestre() {
        return bimestre;
    }

    public void setBimestre(int bimestre) {
        this.bimestre = bimestre;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    // Método para calcular a média do bimestre
    public double calcularMediaBimestre() {
        double soma = 0;
        for (double nota : notasBimestre) {
            soma += nota;
        }
        return soma / notasBimestre.length;
    }

    @Override
    public String toString() {
        String nomeMateria = (materia != null) ? materia.getNome() : "Matéria não atribuída";
        return "Nota{" +
                "aluno=" + aluno.getNome() +
                ", materia=" + nomeMateria +
                ", notasBimestre=" + Arrays.toString(notasBimestre) +
                ", bimestre=" + bimestre +
                ", faltas=" + faltas +
                '}';
    }

}
