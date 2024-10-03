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

    public CadastroMateria() {
        setTitle("Cadastro de Matéria");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

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

        // Ação do botão
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarMateria();
            }
        });

        setVisible(true);
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
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar matéria: " + e.getMessage());
        }
    }
}
