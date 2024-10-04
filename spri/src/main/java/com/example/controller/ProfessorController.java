package com.example.controller;

import com.example.model.Professor;
import com.example.repository.ProfessorRepository;

import java.sql.Connection;

public class ProfessorController {

    private ProfessorRepository professorRepository;

    public ProfessorController(Connection connection) {
        this.professorRepository = new ProfessorRepository(connection);
    }

    public void cadastrarProfessor(Professor professor) {
        professorRepository.cadastrarProfessor(professor);
    }

    public boolean validarCpf(String cpf) {
        return professorRepository.validarCpf(cpf);
    }

    // Método para validar CPF e senha
    public boolean validarLogin(String cpf, String senha) {
        return professorRepository.validarLogin(cpf, senha); // Chama o repositório para validar
    }
}
