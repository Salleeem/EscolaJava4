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
    private JButton cadastrarButton;

    public CadastroAluno() {
        setTitle("Cadastro de Aluno");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

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

        // Botão de cadastro
        cadastrarButton = new JButton("Cadastrar");
        add(cadastrarButton);

        // Ação do botão
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAluno();
            }
        });

        setVisible(true);
    }

    private void cadastrarAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome(nomeField.getText()); // Capturando o nome
        aluno.setCpf(cpfField.getText());   // Capturando o CPF
        aluno.setAnoEscolar(anoEscolarField.getText());
        aluno.setTurno(turnoField.getText());

        // Conectar ao banco de dados e cadastrar aluno
        try (Connection connection = DBConnection.getConnection()) {
            AlunoController alunoController = new AlunoController(connection);
            alunoController.cadastrarAluno(aluno);
            JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar aluno: " + e.getMessage());
        }
    }
}
