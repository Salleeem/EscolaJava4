package com.example.repository;

import com.example.model.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {
    private Connection connection;

    public AlunoRepository(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarAluno(Aluno aluno) {
        // Implementação para cadastrar aluno
    }

    public List<Aluno> buscarTodosOsAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM alunos"; // Ajuste a consulta de acordo com seu banco de dados

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                // Preencher os dados do aluno
                aluno.setCpf(rs.getString("cpf")); // Exemplo
                aluno.setNome(rs.getString("nome")); // Exemplo
                // Adicione outros campos conforme necessário
                alunos.add(aluno);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return alunos;
    }

    public boolean validarLogin(String cpf, String senha) {
        // Implementação para validar login
        return false; // Retorne true ou false conforme necessário
    }
}
