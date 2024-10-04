package com.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import com.example.model.Materia;
import com.example.repository.MateriaRepository;

public class PaginaInicial extends JFrame {

    private List<Materia> materias;

    public PaginaInicial() {
        setTitle("Página Inicial");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        materias = obterMaterias();

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Botão para cadastro de aluno
        JButton cadastroAlunoButton = new JButton("Cadastro de Aluno");
        cadastroAlunoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroAluno(PaginaInicial.this);
                dispose();
            }
        });
        panel.add(cadastroAlunoButton);

        // Botão para cadastro de matéria
        JButton cadastroMateriaButton = new JButton("Cadastro de Matéria");
        cadastroMateriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroMateria(PaginaInicial.this);
                dispose();
            }
        });
        panel.add(cadastroMateriaButton);

        // Botão para cadastro de professor
        JButton cadastroProfessorButton = new JButton("Cadastro de Professor");
        cadastroProfessorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroProfessor(materias, obterConexao(), PaginaInicial.this);
                dispose();
            }
        });
        panel.add(cadastroProfessorButton);

        // Botão para login de professor
        JButton loginProfessorButton = new JButton("Login Professor");
        loginProfessorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginProfessor(PaginaInicial.this, obterConexao()); // Passando a conexão também
                dispose();
            }
        });
        panel.add(loginProfessorButton);

        // Botão para login de aluno
        JButton loginAlunoButton = new JButton("Login Aluno");
        loginAlunoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginAluno(PaginaInicial.this);
                dispose();
            }
        });
        panel.add(loginAlunoButton);

        add(panel, BorderLayout.CENTER);

        JLabel label = new JLabel("Escolha uma opção abaixo:", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        setVisible(true);
    }

    private List<Materia> obterMaterias() {
        List<Materia> listaMaterias = new ArrayList<>();
        try (Connection connection = obterConexao()) {
            if (connection != null) {
                MateriaRepository materiaRepository = new MateriaRepository(connection);
                listaMaterias = materiaRepository.buscarTodasAsMaterias();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaMaterias;
    }

    private Connection obterConexao() {
        String url = "jdbc:postgresql://localhost:5432/escolaa";
        String user = "postgres";
        String password = "postgres";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Falha ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
