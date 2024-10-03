package com.example.model;

public class Materia {
    
    private int id;
    private String nome; 
    private int cargaHoraria;

    // Construtor padrão
    public Materia() {}

    // Construtor com parâmetros
    public Materia(int id, String nome, int cargaHoraria) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                '}';
    }
}
