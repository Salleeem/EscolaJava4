package com.example.model;

public class Pessoa {
    private String nome;
    private String cpf;
    private String senha; // Adicionado o campo senha

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

    public String getSenha() { // Adiciona o getter para senha
        return senha;
    }

    public void setSenha(String senha) { // Adiciona o setter para senha
        this.senha = senha;
    }

    // toString
    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", senha='" + senha + '\'' + // Incluindo a senha na string
                '}';
    }
}
