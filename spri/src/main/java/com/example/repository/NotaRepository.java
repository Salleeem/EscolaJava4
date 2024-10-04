package com.example.repository;

import com.example.model.Nota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotaRepository {
    private Connection connection;

    public NotaRepository(Connection connection) {
        this.connection = connection;
    }

    public void salvarNota(Nota nota) {
        String sql = "INSERT INTO Notas (aluno_id, materia_id, professor_id, valor_nota, bimestre) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, nota.getAluno().getId()); // Assumindo que você tenha um método para obter o ID do aluno
            stmt.setInt(2, nota.getMateria().getId()); // E para a matéria
            stmt.setDouble(3, nota.getValorNota());
            stmt.setInt(4, nota.getBimestre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
