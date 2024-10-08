package com.example.view;

import com.example.controller.AlunoController;
import com.example.model.Aluno;

import javax.swing.*;
import java.awt.*;

public class EditarAluno extends JFrame {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField anoEscolarField;
    private JTextField turnoField;
    private Aluno aluno;

    public EditarAluno(Aluno aluno) {
        this.aluno = aluno;

        // Configurações da JFrame
        setTitle("Editar Aluno");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel de conteúdo
        JPanel panel = new JPanel(new GridLayout(5, 2));

        // Campos de texto para os atributos do aluno
        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField(aluno.getNome());
        panel.add(nomeField);

        panel.add(new JLabel("CPF:"));
        cpfField = new JTextField(aluno.getCpf());
        panel.add(cpfField);

        panel.add(new JLabel("Ano Escolar:"));
        anoEscolarField = new JTextField(aluno.getAnoEscolar());
        panel.add(anoEscolarField);

        panel.add(new JLabel("Turno:"));
        turnoField = new JTextField(aluno.getTurno());
        panel.add(turnoField);

        // Botão de salvar
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarAluno());
        panel.add(btnSalvar);

        add(panel);
    }

    private void salvarAluno() {
        // Atualizar os dados do aluno
        aluno.setNome(nomeField.getText());
        aluno.setCpf(cpfField.getText());
        aluno.setAnoEscolar(anoEscolarField.getText());
        aluno.setTurno(turnoField.getText());

        // Chame o método de edição no controller
        AlunoController.editarAluno(aluno);

        JOptionPane.showMessageDialog(this, "Aluno atualizado com sucesso!");
        dispose(); // Fecha a janela após salvar
    }

}
