package com.example.model;

public class Professor extends Pessoa {
    
    //Atributos Exclusivos do Professor
    private int id;
    private Materia materia; //Chave Estrangeira de Matéria
    private double salario;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    // toString
    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", materia=" + materia +
                ", salario=" + salario +
                "} " + super.toString();
    }
}
