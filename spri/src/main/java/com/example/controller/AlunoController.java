package com.example.controller;

import com.example.model.Aluno;
import com.example.repository.AlunoRepository;
import java.sql.Connection;
import java.util.List;

public class AlunoController {
    
    private AlunoRepository alunoRepository;

    public AlunoController(Connection connection) {
        this.alunoRepository = new AlunoRepository(connection);
    }

    public void cadastrarAluno(Aluno aluno) {
        alunoRepository.cadastrarAluno(aluno);
    }

    public List<Aluno> buscarTodosOsAlunos() {
        return alunoRepository.buscarTodosOsAlunos(); // Chama o repositório para buscar todos os alunos
    }

    // Método para validar CPF e senha
    public boolean validarLogin(String cpf, String senha) {
        return alunoRepository.validarLogin(cpf, senha); // Chama o repositório para validar
    }

    
}
