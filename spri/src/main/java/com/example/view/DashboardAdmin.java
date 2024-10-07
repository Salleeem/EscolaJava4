package com.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardAdmin extends JFrame {
    
    public DashboardAdmin() {
        setTitle("Dashboard do Administrador");
        setSize(400, 500); // Aumentei o tamanho da janela para acomodar mais botões
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Painel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1)); // Alterei o GridLayout para 6 linhas para mais botões
        
        // Botões
        JButton btnCadastrarAluno = new JButton("Cadastrar Aluno");
        JButton btnCadastrarProfessor = new JButton("Cadastrar Professor");
        JButton btnCadastrarMateria = new JButton("Cadastrar Matéria");
        JButton btnListarUsuarios = new JButton("Listar Usuários"); // Novo botão

        // Ações dos Botões
        btnCadastrarAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroAluno().setVisible(true); // Abre a tela de cadastro de aluno
            }
        });

        btnCadastrarProfessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroProfessor().setVisible(true); // Abre a tela de cadastro de professor
            }
        });

        btnCadastrarMateria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroMateria().setVisible(true); // Abre a tela de cadastro de matéria
            }
        });

        // Ação do botão para listar usuários
        btnListarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaUsuarios().setVisible(true); // Abre a tela de listar usuários
            }
        });
        
        // Adicionando os botões ao painel
        panel.add(btnCadastrarAluno);
        panel.add(btnCadastrarProfessor);
        panel.add(btnCadastrarMateria); // Botão para cadastro de matérias
        panel.add(btnListarUsuarios); // Adiciona o botão de listar usuários
        
        // Estilizando o painel
        panel.setBackground(Color.LIGHT_GRAY);
        add(panel);
    }
}
