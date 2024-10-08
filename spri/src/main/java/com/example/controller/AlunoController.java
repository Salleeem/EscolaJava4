package com.example.controller;

import com.example.database.DBConnection;
import com.example.model.Aluno;
import com.example.model.Nota;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class AlunoController {

    // Método para cadastrar aluno no banco de dados
    public static void cadastrarAluno(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, cpf, ano_escolar, turno, senha) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getAnoEscolar());
            stmt.setString(4, aluno.getTurno());
            stmt.setString(5, aluno.getSenha());

            stmt.executeUpdate();
            System.out.println("Aluno cadastrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os alunos cadastrados
    public static List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM alunos";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            // Obtem os Dados dos alunos
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setAnoEscolar(rs.getString("ano_escolar"));
                aluno.setTurno(rs.getString("turno"));
                aluno.setSenha(rs.getString("senha"));
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos; // Retorna a lista de alunos
    }

    // Método para validar login do aluno e retornar seu nome
    public static Object[] validarLogin(String cpf, String senha) {
        String sql = "SELECT id, nome FROM alunos WHERE cpf = ? AND senha = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cpf);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                return new Object[] { nome, id }; // Retorna nome e ID do aluno
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao validar login: " + e.getMessage());
        }
        return null; // Retorna null se as credenciais forem inválidas
    }

    // Método para gerar o boletim escolar do aluno em ".txt"
    public static void gerarBoletimTxt(int idAluno, List<Nota> notas) {
        String nomeArquivo = "boletim_aluno_" + idAluno + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("Boletim Escolar do Aluno ID: " + idAluno);
            writer.newLine();
            writer.write("Notas:");
            writer.newLine();

            for (Nota nota : notas) {
                StringBuilder notasBimestre = new StringBuilder();
                for (double n : nota.getNotasBimestre()) {
                    notasBimestre.append(n).append(" ");
                }
                double media = nota.calcularMediaBimestre(); // Calculo da Média das notas bimestrais
                writer.write("Matéria: " + nota.getMateria().getNome() + ", Bimestre: " + nota.getBimestre() +
                        ", Notas: " + notasBimestre.toString() + ", Faltas: " + nota.getFaltas() +
                        ", Média: " + media);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Boletim gerado com sucesso: " + nomeArquivo);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao gerar boletim: " + e.getMessage());
        }
    }

    // Método para editar os dados de um aluno
    public static void editarAluno(Aluno aluno) {
        String sql = "UPDATE alunos SET nome = ?, cpf = ?, ano_escolar = ?, turno = ?, senha = ? WHERE id = ?";
    
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getAnoEscolar());
            stmt.setString(4, aluno.getTurno());
            stmt.setString(5, aluno.getSenha());
            stmt.setInt(6, aluno.getId());
    
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Aluno atualizado com sucesso!");
            } else {
                System.out.println("Nenhum registro atualizado. Verifique se o ID está correto.");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar o aluno: " + e.getMessage());
        }
    }
    

    // Método para excluir um aluno
    public static void excluirAluno(int idAluno) {
        String sql = "DELETE FROM alunos WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Aluno excluído com sucesso!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao excluir o aluno: " + e.getMessage());
        }
    }

    // Método para buscar aluno pelo seu ID
    public static Aluno buscarAlunoPorId(int id) {
        Aluno aluno = null;
        String sql = "SELECT * FROM alunos WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    aluno = new Aluno();
                    aluno.setId(rs.getInt("id"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setCpf(rs.getString("cpf"));
                    aluno.setAnoEscolar(rs.getString("ano_escolar"));
                    aluno.setTurno(rs.getString("turno"));
                    aluno.setSenha(rs.getString("senha")); // Se necessário
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
        }
        return aluno; // Retorna o aluno encontrado ou null se não encontrado
    }

}
