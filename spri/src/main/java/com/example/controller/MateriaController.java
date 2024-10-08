package com.example.controller;

import com.example.database.DBConnection;
import com.example.model.Materia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MateriaController {

    //Método para o cadastro da Matéria
    public static void cadastrarMateria(Materia materia) {
        String sql = "INSERT INTO materias (nome) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, materia.getNome());
            stmt.executeUpdate();
            System.out.println("Matéria cadastrada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Método para listar todas as Matérias
    public static List<Materia> listarMaterias() {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM materias";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Materia materia = new Materia();
                materia.setId(rs.getInt("id"));
                materia.setNome(rs.getString("nome"));
                materias.add(materia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materias;
    }

    //Método para listar as matérias atribuidas a um Professor
    public static List<Materia> listarMateriasPorProfessor(int idProfessor) {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT m.id, m.nome FROM materias m " +
                     "JOIN professores_materias pm ON m.id = pm.id_materia " +
                     "WHERE pm.id_professor = ?";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProfessor);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setId(rs.getInt("id"));
                materia.setNome(rs.getString("nome"));
                materias.add(materia);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao listar matérias do professor.");
        }
    
        return materias;
    }
    
}
