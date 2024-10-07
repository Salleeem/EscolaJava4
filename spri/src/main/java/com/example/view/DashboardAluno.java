package com.example.view;

import com.example.controller.AlunoController;
import com.example.controller.NotaController;
import com.example.model.Nota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DashboardAluno extends JFrame {
    private JTable tableNotas;

    public DashboardAluno(String nome, int idAluno) {
        // Configurações da JFrame
        setTitle("Dashboard Aluno");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Mensagem de boas-vindas
        JLabel lblBemVindo = new JLabel("Bem-vindo Aluno: " + nome);
        lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);

        // Tabela para exibir notas
        String[] columnNames = {"Matéria", "Bimestre", "Notas", "Faltas", "Média"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tableNotas = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableNotas);
        scrollPane.setPreferredSize(new Dimension(550, 200));

        // Carregar notas do aluno
        carregarNotas(idAluno, model);

        // Botão para gerar o boletim
        JButton btnGerarBoletim = new JButton("Gerar Boletim");
        btnGerarBoletim.addActionListener(e -> {
            List<Nota> notas = NotaController.listarNotasAluno(idAluno);
            AlunoController.gerarBoletimTxt(idAluno, notas);
        });

        // Botão de voltar
        JButton btnVoltar = new JButton("Logout");
        btnVoltar.addActionListener(e -> {
            new LoginAluno().setVisible(true);
            dispose(); // Fecha a dashboard
        });

        // Layout e adição dos componentes
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(lblBemVindo);
        add(scrollPane);
        add(btnGerarBoletim); // Adiciona o botão de gerar boletim
        add(btnVoltar); // Adiciona o botão de voltar
    }

    private void carregarNotas(int idAluno, DefaultTableModel model) {
        List<Nota> notas = NotaController.listarNotasAluno(idAluno);
        for (Nota nota : notas) {
            StringBuilder notasBimestre = new StringBuilder();
            for (double n : nota.getNotasBimestre()) {
                notasBimestre.append(n).append(" ");
            }
            double media = nota.calcularMediaBimestre();
            model.addRow(new Object[]{
                nota.getMateria().getNome(),
                nota.getBimestre(),
                notasBimestre.toString(),
                nota.getFaltas(),
                media
            });
        }
    }
}
