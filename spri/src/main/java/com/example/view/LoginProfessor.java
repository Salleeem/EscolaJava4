package com.example.view;

import com.example.controller.ProfessorController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginProfessor extends JFrame {
    private JTextField txtCpf;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private JButton btnVoltar; // Adicionando o botão de voltar

    public LoginProfessor() {
        // Configurações da JFrame
        setTitle("Login Professor");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criando os campos de CPF e Senha
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(80, 80, 80, 25);
        txtCpf = new JTextField();
        txtCpf.setBounds(140, 80, 160, 25);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(80, 120, 80, 25);
        txtSenha = new JPasswordField();
        txtSenha.setBounds(140, 120, 160, 25);

        // Botão de login
        btnLogin = new JButton("Login");
        btnLogin.setBounds(140, 160, 100, 30);

        // Botão de voltar
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(140, 200, 100, 30); // Posicionando o botão de voltar

        // Adicionando ActionListener ao botão de login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = txtCpf.getText();
                String senha = new String(txtSenha.getPassword());

                // Verifique se as credenciais estão corretas e obtenha o nome e o ID
                String nome = ProfessorController.validarLogin(cpf, senha);
                int idProfessor = ProfessorController.getIdProfessor(cpf); // Adiciona método para obter ID

                if (nome != null) {
                    // Redireciona para o dashboard do professor
                    new DashboardProfessor(nome, idProfessor).setVisible(true);
                    dispose(); // Fecha a janela de login
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciais inválidas!");
                }
            }
        });

        // Adicionando ActionListener ao botão de voltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela de login
                new PaginaInicial().setVisible(true); // Abre a tela inicial
            }
        });

        // Layout e adição dos componentes
        setLayout(null);
        add(lblCpf);
        add(txtCpf);
        add(lblSenha);
        add(txtSenha);
        add(btnLogin);
        add(btnVoltar); // Adiciona o botão de voltar
    }
}
