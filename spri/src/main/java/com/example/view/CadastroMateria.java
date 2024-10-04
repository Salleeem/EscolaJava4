package com.example.view;

import com.example.controller.MateriaController;
import com.example.database.DBConnection;
import com.example.model.Materia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class CadastroMateria extends JFrame {
    private JTextField nomeField;
    private JTextField cargaHorariaField;
    private JButton cadastrarButton;
    private JButton voltarButton; // Botão para voltar à página inicial

    public CadastroMateria(PaginaInicial paginaInicial) { // Recebe a referência da página inicial
        setTitle("Cadastro de Matéria");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela
        setLayout(new GridLayout(4, 2, 10, 10)); // 4 linhas, 2 colunas com espaçamento

        // Campos de entrada
        add(new JLabel("Nome da Matéria:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("Carga Horária:"));
        cargaHorariaField = new JTextField();
        add(cargaHorariaField);

        // Botão de cadastro
        cadastrarButton = new JButton("Cadastrar");
        add(cadastrarButton);

        // Ação do botão de cadastro
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarMateria();
            }
        });

        // Botão para voltar à página inicial
        voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paginaInicial.setVisible(true); // Torna a página inicial visível
                dispose(); // Fecha a janela de cadastro
            }
        });
        add(voltarButton); // Adiciona o botão "Voltar"

        setVisible(true); // Torna a janela visível
    }

    private void cadastrarMateria() {
        Materia materia = new Materia();
        materia.setNome(nomeField.getText());
        materia.setCargaHoraria(Integer.parseInt(cargaHorariaField.getText()));

        // Conectar ao banco de dados e cadastrar matéria
        try (Connection connection = DBConnection.getConnection()) {
            MateriaController materiaController = new MateriaController(connection);
            materiaController.cadastrarMateria(materia);
            JOptionPane.showMessageDialog(this, "Matéria cadastrada com sucesso!");
            clearFields(); // Limpa os campos após o cadastro
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar matéria: " + e.getMessage());
        }
    }

    private void clearFields() {
        nomeField.setText(""); // Limpa o campo de nome
        cargaHorariaField.setText(""); // Limpa o campo de carga horária
    }
}
