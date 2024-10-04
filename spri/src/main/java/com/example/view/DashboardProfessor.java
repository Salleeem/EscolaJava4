package com.example.view;

import com.example.controller.AlunoController;
import com.example.controller.MateriaController;
import com.example.controller.NotaController;
import com.example.model.Aluno;
import com.example.model.Materia;
import com.example.model.Nota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.List;

public class DashboardProfessor extends JFrame {
    private JComboBox<Aluno> alunoComboBox;
    private JComboBox<Materia> materiaComboBox;
    private JTextField notaField;
    private JTextField bimestreField;
    private DefaultTableModel modeloTabela;
    private Connection connection;

    public DashboardProfessor(String cpf, Connection connection) {
        this.connection = connection;
        setTitle("Dashboard do Professor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createWelcomeLabel(cpf), BorderLayout.NORTH);
        add(createAtribuirNotaPanel(), BorderLayout.CENTER);
        add(createNotasTable(), BorderLayout.SOUTH);
        add(createLogoutButton(), BorderLayout.NORTH);

        setVisible(true);
    }

    private JLabel createWelcomeLabel(String cpf) {
        JLabel welcomeLabel = new JLabel("Bem-vindo, Professor! Seu CPF: " + cpf);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return welcomeLabel;
    }

    private JPanel createAtribuirNotaPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        alunoComboBox = new JComboBox<>();
        materiaComboBox = new JComboBox<>();
        carregarAlunos();
        carregarMaterias();

        panel.add(new JLabel("Selecione o Aluno:"));
        panel.add(alunoComboBox);
        panel.add(new JLabel("Selecione a Matéria:"));
        panel.add(materiaComboBox);
        panel.add(new JLabel("Nota:"));
        notaField = new JTextField(5);
        panel.add(notaField);
        panel.add(new JLabel("Bimestre:"));
        bimestreField = new JTextField(5);
        panel.add(bimestreField);
        panel.add(createAtribuirNotaButton());
        return panel;
    }

    private JButton createAtribuirNotaButton() {
        JButton button = new JButton("Atribuir Nota");
        button.addActionListener(this::atribuirNota);
        return button;
    }

    private JScrollPane createNotasTable() {
        modeloTabela = new DefaultTableModel(new String[]{"Aluno", "Matéria", "Nota", "Bimestre"}, 0);
        JTable tabelaNotas = new JTable(modeloTabela);
        return new JScrollPane(tabelaNotas);
    }

    private JButton createLogoutButton() {
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> voltarParaLogin());
        return logoutButton;
    }

    private void carregarAlunos() {
        AlunoController alunoController = new AlunoController(connection);
        List<Aluno> alunos = alunoController.buscarTodosOsAlunos();
        for (Aluno aluno : alunos) {
            alunoComboBox.addItem(aluno);
        }
    }

    private void carregarMaterias() {
        MateriaController materiaController = new MateriaController(connection);
        List<Materia> materias = materiaController.buscarTodasAsMaterias();
        for (Materia materia : materias) {
            materiaComboBox.addItem(materia);
        }
    }

    private void atribuirNota(ActionEvent e) {
        Aluno alunoSelecionado = (Aluno) alunoComboBox.getSelectedItem();
        Materia materiaSelecionada = (Materia) materiaComboBox.getSelectedItem();

        if (alunoSelecionado == null || materiaSelecionada == null) {
            showError("Por favor, selecione um aluno e uma matéria.");
            return;
        }

        try {
            double nota = Double.parseDouble(notaField.getText());
            int bimestre = Integer.parseInt(bimestreField.getText());

            Nota novaNota = new Nota(alunoSelecionado, materiaSelecionada, nota, bimestre);
            NotaController notaController = new NotaController(connection);
            notaController.salvarNota(novaNota);

            modeloTabela.addRow(new Object[]{
                    alunoSelecionado.getNome(),
                    materiaSelecionada.getNome(),
                    nota,
                    bimestre
            });

            JOptionPane.showMessageDialog(this, "Nota atribuída com sucesso ao aluno " + alunoSelecionado.getNome());
            limparCampos();
        } catch (NumberFormatException ex) {
            showError("Por favor, insira valores válidos para a nota e o bimestre.");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void limparCampos() {
        notaField.setText("");
        bimestreField.setText("");
        alunoComboBox.setSelectedIndex(0);
        materiaComboBox.setSelectedIndex(0);
    }

    private void voltarParaLogin() {
        new LoginProfessor(new PaginaInicial(), connection);
        dispose();
    }
}
