// src/controller/AlunoController.java
package com.example.controller;

import com.example.model.Aluno;
import com.example.repository.AlunoRepository;
import java.sql.Connection;

public class AlunoController {
    
    private AlunoRepository alunoRepository;

    public AlunoController(Connection connection) {
        this.alunoRepository = new AlunoRepository(connection);
    }

    public void cadastrarAluno(Aluno aluno) {
        alunoRepository.cadastrarAluno(aluno);
    }
}
