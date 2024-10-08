package com.example.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardAdmin extends JFrame {
    
    public DashboardAdmin() {
        // Configurações da JFrame
        setTitle("Dashboard do Administrador");
        setSize(400, 500); // Tamanho da janela
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Título
        JLabel titleLabel = new JLabel("Dashboard do Administrador", JLabel.CENTER);
        titleLabel.setBounds(0, 20, 400, 30); // Título centralizado no topo
        add(titleLabel);
        
        // Botões
        JButton btnCadastrarAluno = new JButton("Cadastrar Aluno");
        btnCadastrarAluno.setBounds(120, 80, 150, 30); // Botão posicionado

        JButton btnCadastrarProfessor = new JButton("Cadastrar Professor");
        btnCadastrarProfessor.setBounds(120, 120, 150, 30); // Botão posicionado

        JButton btnCadastrarMateria = new JButton("Cadastrar Matéria");
        btnCadastrarMateria.setBounds(120, 160, 150, 30); // Botão posicionado

        JButton btnListarUsuarios = new JButton("Listar Usuários");
        btnListarUsuarios.setBounds(120, 200, 150, 30); // Botão posicionado

        JButton btnVoltar = new JButton("Logout");
        btnVoltar.setBounds(120, 240, 150, 30); // Botão de voltar

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

        // Ação do botão de voltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                new PaginaInicial().setVisible(true); // Abre a tela inicial
            }
        });

        // Layout e adição dos componentes
        setLayout(null);
        add(btnCadastrarAluno);
        add(btnCadastrarProfessor);
        add(btnCadastrarMateria);
        add(btnListarUsuarios);
        add(btnVoltar);
        add(titleLabel); // Adiciona o título
    }

}
