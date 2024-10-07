package com.example.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginaInicial extends JFrame {

    private JButton btnAdminLogin;
    private JButton btnAlunoLogin;
    private JButton btnProfessorLogin;

    public PaginaInicial() {
        // Configurações da JFrame
        setTitle("Sistema de Gestão Escolar");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criando o JLabel de boas-vindas
        JLabel lblBemVindo = new JLabel("Bem-vindo ao Sistema de Gestão Escolar", SwingConstants.CENTER);
        lblBemVindo.setBounds(50, 10, 300, 30); // Ajuste a posição e o tamanho

        // Criando o botão para login do administrador
        btnAdminLogin = new JButton("Login Administrador");
        btnAdminLogin.setBounds(120, 60, 160, 40);
        
        // Criando o botão para login do aluno
        btnAlunoLogin = new JButton("Login Aluno");
        btnAlunoLogin.setBounds(120, 120, 160, 40);

        // Criando o botão para login do professor
        btnProfessorLogin = new JButton("Login Professor");
        btnProfessorLogin.setBounds(120, 180, 160, 40);

        // Adicionando o listener para o botão do administrador
        btnAdminLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginAdmin().setVisible(true);
                dispose(); // Fecha a página inicial
            }
        });

        // Adicionando o listener para o botão do aluno
        btnAlunoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginAluno().setVisible(true);
                dispose(); // Fecha a página inicial
            }
        });

        // Adicionando o listener para o botão do professor
        btnProfessorLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginProfessor().setVisible(true);
                dispose(); // Fecha a página inicial
            }
        });

        // Layout e adição dos componentes
        setLayout(null);
        add(lblBemVindo);  // Adiciona o JLabel de boas-vindas
        add(btnAdminLogin);
        add(btnAlunoLogin);
        add(btnProfessorLogin);
    }

    // Adiciona o método para exibir a janela
    public void showPage() {
        setVisible(true);
    }
}
