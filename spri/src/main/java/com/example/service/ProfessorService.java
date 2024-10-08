package com.example.service;

import com.example.controller.ProfessorController;
import com.example.model.Professor;
import java.util.List;

public class ProfessorService {

    public void cadastrarProfessor(Professor professor) {
        if (professor == null || professor.getNome() == null || professor.getCpf() == null) {
            throw new IllegalArgumentException("Dados do professor inválidos.");
        }
        ProfessorController.cadastrarProfessor(professor);  // Chamada estática
    }

    public String validarLogin(String cpf, String senha) {
        if (cpf == null || senha == null) {
            throw new IllegalArgumentException("CPF e senha não podem ser nulos.");
        }
        return ProfessorController.validarLogin(cpf, senha);  // Chamada estática
    }

    public int getIdProfessor(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("CPF não pode ser nulo.");
        }
        return ProfessorController.getIdProfessor(cpf);  // Chamada estática
    }

    public List<Professor> listarProfessores() {
        return ProfessorController.listarProfessores();  // Chamada estática
    }

    public void editarProfessor(Professor professor) {
        if (professor == null || professor.getId() <= 0) {
            throw new IllegalArgumentException("Professor inválido.");
        }
        ProfessorController.editarProfessor(professor);  // Chamada estática
    }

    public void excluirProfessor(int idProfessor) {
        if (idProfessor <= 0) {
            throw new IllegalArgumentException("ID do professor inválido.");
        }
        ProfessorController.excluirProfessor(idProfessor);  // Chamada estática
    }

    public Professor buscarProfessorPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return ProfessorController.buscarProfessorPorId(id);  // Chamada estática
    }
}
