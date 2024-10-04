package com.example.view;

import com.example.controller.AlunoController;
import com.example.database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class LoginAluno extends JFrame {
    private JTextField cpfField;
    private JTextField senhaField;
    private JButton loginButton;
    private JButton voltarButton; // Novo botão para voltar
    private PaginaInicial paginaInicial; // Atributo para a página inicial

    public LoginAluno(PaginaInicial paginaInicial) {
        this.paginaInicial = paginaInicial; // Inicializa o atributo
        setTitle("Login do Aluno");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2)); // Ajusta o layout para 4 linhas

        // Campo para CPF
        add(new JLabel("CPF:"));
        cpfField = new JTextField();
        add(cpfField);

        // Campo para Senha
        add(new JLabel("Senha:"));
        senhaField = new JTextField();
        add(senhaField);

        // Botão de login
        loginButton = new JButton("Login");
        add(loginButton);

        // Ação do botão de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });

        // Botão de voltar
        voltarButton = new JButton("Voltar");
        add(voltarButton);

        // Ação do botão de voltar
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaPaginaInicial(); // Chama o método para voltar
            }
        });

        setVisible(true);
    }

    private void realizarLogin() {
        String cpf = cpfField.getText();
        String senha = senhaField.getText();

        // Conectar ao banco de dados e validar o CPF e a senha
        try (Connection connection = DBConnection.getConnection()) {
            AlunoController alunoController = new AlunoController(connection);
            if (alunoController.validarLogin(cpf, senha)) {
                new DashboardAluno(cpf); // Chama a dashboard do aluno
                dispose(); // Fecha a tela de login
            } else {
                JOptionPane.showMessageDialog(this, "CPF ou senha inválidos! Tente novamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    private void voltarParaPaginaInicial() {
        paginaInicial.setVisible(true); // Torna a página inicial visível
        dispose(); // Fecha a tela de login
    }
}
