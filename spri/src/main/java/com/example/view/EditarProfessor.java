package com.example.view;

import com.example.controller.ProfessorController;
import com.example.model.Professor;

import javax.swing.*;
import java.awt.*;

public class EditarProfessor extends JFrame {
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtSalario;
    private Professor professor;

    public EditarProfessor(Professor professor) {
        this.professor = professor;

        setTitle("Editar Professor");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Nome:"));
        txtNome = new JTextField(professor.getNome());
        panel.add(txtNome);

        panel.add(new JLabel("CPF:"));
        txtCpf = new JTextField(professor.getCpf());
        panel.add(txtCpf);

        panel.add(new JLabel("Salário:"));
        txtSalario = new JTextField(String.valueOf(professor.getSalario()));
        panel.add(txtSalario);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvar());

        panel.add(btnSalvar);
        add(panel);
    }

    private void salvar() {
        professor.setNome(txtNome.getText());
        professor.setCpf(txtCpf.getText());
        professor.setSalario(Double.parseDouble(txtSalario.getText())); // Converte o texto para double

        ProfessorController.editarProfessor(professor);
        dispose(); // Fecha a janela após salvar
    }
}
