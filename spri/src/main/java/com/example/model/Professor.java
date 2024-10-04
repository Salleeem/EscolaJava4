package com.example.model;

public class Professor extends Pessoa {
    
    private int id;
    private Materia materia; // A maté  ria que o professor leciona
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
