package com.example.model;

public class Admin {

    //Atributos
    private String nome;
    private String cpf;
    private String senha;

    // Construtor
    public Admin(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Método para validação da senha (opcional)
    public boolean validarSenha(String senha) {
        return this.senha.equals(senha);
    }
}

