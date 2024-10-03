package com.example.repository;

import com.example.model.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoRepository {
    
    private Connection connection;

    // Construtor para receber a conexão do banco de dados
    public AlunoRepository(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarAluno(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, cpf, ano_escolar, turno) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome()); // Adicionando nome
            stmt.setString(2, aluno.getCpf());  // Adicionando cpf
            stmt.setString(3, aluno.getAnoEscolar());
            stmt.setString(4, aluno.getTurno());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
