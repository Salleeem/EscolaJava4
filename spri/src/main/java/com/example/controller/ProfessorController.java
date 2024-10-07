package com.example.controller;

import com.example.database.DBConnection;
import com.example.model.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorController {

    // Método para cadastrar professor no banco de dados
    public static void cadastrarProfessor(Professor professor) {
        if (professor == null) {
            System.out.println("Professor não pode ser nulo.");
            return;
        }

        String sql = "INSERT INTO professores (nome, cpf, salario, senha) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Validação básica
            if (professor.getNome() == null || professor.getCpf() == null || 
                professor.getSalario() < 0 || professor.getSenha() == null) {
                System.out.println("Todos os campos devem ser preenchidos corretamente.");
                return;
            }

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getCpf());
            stmt.setDouble(3, professor.getSalario());
            stmt.setString(4, professor.getSenha());

            stmt.executeUpdate();
            System.out.println("Professor cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar professor: " + e.getMessage());
        }
    }

    // Método para validar login do professor e retornar seu nome
    public static String validarLogin(String cpf, String senha) {
        String sql = "SELECT * FROM professores WHERE cpf = ? AND senha = ?";
        String nome = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nome = rs.getString("nome"); // Retorna o nome do professor
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nome; // Retorna null se não encontrar
    }

    // Método para obter o ID do professor com base no CPF
    public static int getIdProfessor(String cpf) {
        String sql = "SELECT id FROM professores WHERE cpf = ?";
        int idProfessor = -1; // Valor padrão para não encontrado

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    idProfessor = rs.getInt("id"); // Retorna o ID do professor
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idProfessor; // Retorna -1 se não encontrado
    }

    // Método para associar um professor a uma matéria
    public static void associarProfessorMateria(int idProfessor, int idMateria) {
        String sql = "INSERT INTO professores_materias (id_professor, id_materia) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProfessor);
            stmt.setInt(2, idMateria);
            stmt.executeUpdate();
            System.out.println("Associação criada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao associar professor a matéria: " + e.getMessage());
        }
    }

    // Método para listar todos os professores
    public static List<Professor> listarProfessores() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professores"; // SQL para selecionar todos os professores

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Criar um novo objeto Professor a partir dos dados do ResultSet
                Professor professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setCpf(rs.getString("cpf"));
                professor.setSalario(rs.getDouble("salario"));
                professor.setSenha(rs.getString("senha"));

                professores.add(professor); // Adicionar o professor à lista
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar professores: " + e.getMessage());
        }

        return professores; // Retorna a lista de professores
    }

    // Método para listar matérias de um professor com base no ID
public static List<String> listarMateriasPorProfessor(int idProfessor) {
    List<String> materias = new ArrayList<>();
    String sql = "SELECT m.nome FROM materias m " +
                 "JOIN professores_materias pm ON m.id = pm.id_materia " +
                 "WHERE pm.id_professor = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idProfessor);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                materias.add(rs.getString("nome")); // Adiciona o nome da matéria à lista
            }
        }

    } catch (SQLException e) {
        System.err.println("Erro ao listar matérias do professor: " + e.getMessage());
    }

    return materias; // Retorna a lista de matérias
}

}
