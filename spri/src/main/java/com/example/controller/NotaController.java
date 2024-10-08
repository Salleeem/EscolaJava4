package com.example.controller;

import com.example.model.Aluno;
import com.example.model.Materia;
import com.example.model.Nota;
import com.example.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotaController {

    //Método para atribuir uma nota ao Aluno
    public static void atribuirNota(Nota nota) {
        String sql = "INSERT INTO notas (id_aluno, id_materia, bimestre, nota1, nota2, nota3, nota4, nota5, faltas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, nota.getAluno().getId());
            pstmt.setInt(2, nota.getMateria().getId());
            pstmt.setInt(3, nota.getBimestre());
                
            double[] notasBimestre = nota.getNotasBimestre();
            pstmt.setDouble(4, notasBimestre[0]);
            pstmt.setDouble(5, notasBimestre[1]);
            pstmt.setDouble(6, notasBimestre[2]);
            pstmt.setDouble(7, notasBimestre[3]);
            pstmt.setDouble(8, notasBimestre[4]);

            pstmt.setInt(9, nota.getFaltas());

            pstmt.executeUpdate();
            System.out.println("Nota atribuída com sucesso ao banco de dados.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atribuir nota: " + e.getMessage());
        }
    }

    // Método para listar as notas de um aluno
    public static List<Nota> listarNotasAluno(int idAluno) {
        List<Nota> notas = new ArrayList<>();
        String sql = "SELECT n.bimestre, n.faltas, n.nota1, n.nota2, n.nota3, n.nota4, n.nota5, m.id AS id_materia, m.nome AS nome_materia " +
                     "FROM notas n JOIN materias m ON n.id_materia = m.id WHERE n.id_aluno = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idAluno);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno(); // Você pode preencher os dados do aluno se necessário
                aluno.setId(idAluno); // Preenche o ID do aluno
                Materia materia = new Materia(); 
                materia.setId(rs.getInt("id_materia"));
                materia.setNome(rs.getString("nome_materia"));

                double[] notasBimestre = new double[5];
                notasBimestre[0] = rs.getDouble("nota1");
                notasBimestre[1] = rs.getDouble("nota2");
                notasBimestre[2] = rs.getDouble("nota3");
                notasBimestre[3] = rs.getDouble("nota4");
                notasBimestre[4] = rs.getDouble("nota5");

                Nota nota = new Nota(aluno, materia, notasBimestre, rs.getInt("bimestre"), rs.getInt("faltas"));
                notas.add(nota);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao listar notas: " + e.getMessage());
        }
        return notas;
    }
}
