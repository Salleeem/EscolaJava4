package com.example.view;

import com.example.controller.ProfessorController;
import com.example.database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class LoginProfessor extends JFrame {
    private Connection connection; // Conexão com o banco de dados
    private JTextField cpfField;
    private JPasswordField senhaField; // Campo para a senha
    private JButton loginButton;
    private JButton voltarButton; // Novo botão para voltar
    private PaginaInicial paginaInicial; // Referência para a página inicial

    // Construtor modificado para aceitar a conexão e a referência da PaginaInicial
    public LoginProfessor(PaginaInicial paginaInicial, Connection connection) {
        this.connection = connection; // Armazena a conexão
        this.paginaInicial = paginaInicial; // Armazena a referência
        setTitle("Login do Professor");
        setSize(300, 200); // Aumenta o tamanho para acomodar o novo campo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2)); // Ajusta o layout para 5 linhas

        // Campo para CPF
        add(new JLabel("CPF:"));
        cpfField = new JTextField();
        add(cpfField);

        // Campo para Senha
        add(new JLabel("Senha:"));
        senhaField = new JPasswordField(); // Usando JPasswordField para ocultar a senha
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
        String senha = new String(senhaField.getPassword()); // Obtém a senha do campo

        // Validar CPF e senha
        try {
            ProfessorController professorController = new ProfessorController(connection);
            if (professorController.validarLogin(cpf, senha)) { // Atualiza a chamada para validarLogin
                new DashboardProfessor(cpf, connection); // Passa a conexão ao Dashboard
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
