package com.example.repository;

import com.example.model.Professor;
import com.example.model.Materia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfessorRepository {

    private Connection connection;

    public ProfessorRepository(Connection connection) {
        this.connection = connection;
    }

    // Método para cadastrar um professor
    public void cadastrarProfessor(Professor professor) {
        String sql = "INSERT INTO professor (nome, cpf, salario, id_materia) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getCpf());
            stmt.setDouble(3, professor.getSalario());
            stmt.setInt(4, professor.getMateria().getId()); // ID da matéria

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
