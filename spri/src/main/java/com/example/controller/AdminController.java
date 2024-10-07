package com.example.controller;

import com.example.database.DBConnection;
import com.example.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController {

    // Método para validar login do administrador via banco de dados
    public static boolean validarLogin(String cpf, String senha) {
        String sql = "SELECT * FROM administradores WHERE cpf = ? AND senha = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true; // Admin encontrado
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Admin não encontrado
    }

    // Método para inserir um novo administrador (caso necessário)
    public static void cadastrarAdmin(Admin admin) {
        String sql = "INSERT INTO administradores (nome, cpf, senha) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, admin.getNome());
            stmt.setString(2, admin.getCpf());
            stmt.setString(3, admin.getSenha());
            stmt.executeUpdate();
            System.out.println("Administrador cadastrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
