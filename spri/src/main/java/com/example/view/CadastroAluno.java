package com.example.view;

import com.example.controller.AlunoController;
import com.example.database.DBConnection;
import com.example.model.Aluno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class CadastroAluno extends JFrame {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField anoEscolarField;
    private JTextField turnoField;
    private JPasswordField senhaField; // Campo para senha
    private JButton cadastrarButton;
    private JButton voltarButton;

    public CadastroAluno(PaginaInicial paginaInicial) {
        setTitle("Cadastro de Aluno");
        setSize(400, 350); // Aumentando a altura para incluir o campo de senha
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10)); // 7 linhas, 2 colunas

        // Campos de entrada
        add(new JLabel("Nome:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("CPF:"));
        cpfField = new JTextField();
        add(cpfField);

        add(new JLabel("Ano Escolar:"));
        anoEscolarField = new JTextField();
        add(anoEscolarField);

        add(new JLabel("Turno:"));
        turnoField = new JTextField();
        add(turnoField);

        add(new JLabel("Senha:")); // Rótulo para senha
        senhaField = new JPasswordField(); // Usando JPasswordField para entrada de senha
        add(senhaField);

        // Botão de cadastro
        cadastrarButton = new JButton("Cadastrar");
        add(cadastrarButton);

        // Ação do botão de cadastro
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAluno();
            }
        });

        // Botão para voltar à página inicial
        voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paginaInicial.setVisible(true);
                dispose();
            }
        });
        add(voltarButton);

        setVisible(true);
    }

    private void cadastrarAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome(nomeField.getText());
        aluno.setCpf(cpfField.getText());
        aluno.setAnoEscolar(anoEscolarField.getText());
        aluno.setTurno(turnoField.getText());
        aluno.setSenha(new String(senhaField.getPassword())); // Capturando a senha

        // Conectar ao banco de dados e cadastrar aluno
        try (Connection connection = DBConnection.getConnection()) {
            AlunoController alunoController = new AlunoController(connection);
            alunoController.cadastrarAluno(aluno);
            JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    private void clearFields() {
        nomeField.setText("");
        cpfField.setText("");
        anoEscolarField.setText("");
        turnoField.setText("");
        senhaField.setText(""); // Limpando o campo de senha
    }
}
