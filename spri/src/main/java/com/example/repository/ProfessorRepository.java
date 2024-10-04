package com.example.repository;

import com.example.model.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorRepository {

    private Connection connection;

    public ProfessorRepository(Connection connection) {
        this.connection = connection;
    }

    // Método para cadastrar um professor
    public void cadastrarProfessor(Professor professor) {
        String sql = "INSERT INTO professor (nome, cpf, salario, id_materia, senha) VALUES (?, ?, ?, ?, ?)"; // Adicionando senha

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getCpf());
            stmt.setDouble(3, professor.getSalario());
            stmt.setInt(4, professor.getMateria().getId()); // ID da matéria
            stmt.setString(5, professor.getSenha()); // Adicionando a senha
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validarCpf(String cpf) {
        String query = "SELECT * FROM professor WHERE cpf = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Retorna true se o CPF existir
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de erro
        }
    }

    // Método para validar CPF e senha
    public boolean validarLogin(String cpf, String senha) {
        String query = "SELECT * FROM professor WHERE cpf = ? AND senha = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cpf);
            statement.setString(2, senha); // Verifica a senha
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Retorna true se o CPF e a senha forem válidos
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de erro
        }
    }
}
