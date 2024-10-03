package com.example.repository;

import com.example.model.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MateriaRepository {

    private Connection connection;

    public MateriaRepository(Connection connection) {
        this.connection = connection;
    }

    // Método para cadastrar uma nova matéria
    public void cadastrarMateria(Materia materia) {
        String sql = "INSERT INTO materias (nome, carga_horaria) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, materia.getNome());
            stmt.setInt(2, materia.getCargaHoraria());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar todas as matérias cadastradas
    public List<Materia> buscarTodasAsMaterias() {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM materias";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Percorre os resultados do ResultSet e adiciona à lista de matérias
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int cargaHoraria = rs.getInt("carga_horaria");
                
                // Adiciona a matéria à lista
                Materia materia = new Materia(id, nome, cargaHoraria);
                materias.add(materia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materias;
    }
}
