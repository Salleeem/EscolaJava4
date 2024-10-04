package com.example.controller;

import com.example.model.Nota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotaController {
    private Connection connection;

    public NotaController(Connection connection) {
        this.connection = connection;
    }

    // Método para verificar se o aluno existe
    public boolean alunoExiste(int alunoId) {
        String sql = "SELECT COUNT(*) FROM alunos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se o aluno existir
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void salvarNota(Nota nota) {
        if (!alunoExiste(nota.getAluno().getId())) {
            System.out.println("Erro: O aluno com ID " + nota.getAluno().getId() + " não existe.");
            return;
        }

        String sql = "INSERT INTO nota (aluno_id, materia_id, valor, bimestre) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, nota.getAluno().getId()); // Obtém o ID do aluno
            stmt.setInt(2, nota.getMateria().getId()); // Obtém o ID da matéria
            stmt.setDouble(3, nota.getValorNota()); // Obtém o valor da nota
            stmt.setInt(4, nota.getBimestre()); // Define o bimestre diretamente como um int

            // Executa a atualização
            stmt.executeUpdate();
            System.out.println("Nota atribuída com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atribuir a nota.");
        }
    }
}
