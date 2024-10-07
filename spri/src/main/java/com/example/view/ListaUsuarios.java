package com.example.view;

import com.example.controller.AlunoController;
import com.example.controller.ProfessorController;
import com.example.model.Aluno;
import com.example.model.Professor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaUsuarios extends JFrame {
    public ListaUsuarios() {
        // Configurações da JFrame
        setTitle("Lista de Usuários");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criar tabelas para alunos e professores
        String[] columnNamesAlunos = {"ID", "Nome", "CPF", "Ano Escolar", "Turno"};
        String[] columnNamesProfessores = {"ID", "Nome", "CPF", "Matérias"}; // Atualizado para plural

        DefaultTableModel modelAlunos = new DefaultTableModel(columnNamesAlunos, 0);
        DefaultTableModel modelProfessores = new DefaultTableModel(columnNamesProfessores, 0);

        JTable tableAlunos = new JTable(modelAlunos);
        JTable tableProfessores = new JTable(modelProfessores);

        // Carregar dados dos alunos
        List<Aluno> alunos = AlunoController.listarAlunos();
        for (Aluno aluno : alunos) {
            modelAlunos.addRow(new Object[]{
                aluno.getId(),
                aluno.getNome(),
                aluno.getCpf(),
                aluno.getAnoEscolar(),
                aluno.getTurno()
            });
        }

        // Carregar dados dos professores
        List<Professor> professores = ProfessorController.listarProfessores();
        for (Professor professor : professores) {
            // Obter as matérias do professor
            List<String> materias = ProfessorController.listarMateriasPorProfessor(professor.getId());
            String materiasStr = String.join(", ", materias); // Unir as matérias em uma única string

            modelProfessores.addRow(new Object[]{
                professor.getId(),
                professor.getNome(),
                professor.getCpf(),
                materiasStr // Adicionar a string das matérias
            });
        }

        // Layout e adição dos componentes
        setLayout(new GridLayout(3, 1)); // Dividir em 3 linhas
        JScrollPane scrollAlunos = new JScrollPane(tableAlunos);
        JScrollPane scrollProfessores = new JScrollPane(tableProfessores);

        JPanel panelAlunos = new JPanel(new BorderLayout());
        panelAlunos.add(new JLabel("Lista de Alunos"), BorderLayout.NORTH);
        panelAlunos.add(scrollAlunos, BorderLayout.CENTER);

        JPanel panelProfessores = new JPanel(new BorderLayout());
        panelProfessores.add(new JLabel("Lista de Professores"), BorderLayout.NORTH);
        panelProfessores.add(scrollProfessores, BorderLayout.CENTER);

        // Botão de voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> dispose()); // Fecha a janela ao clicar no botão
        JPanel panelBotao = new JPanel();
        panelBotao.add(btnVoltar);

        add(panelAlunos);
        add(panelProfessores);
        add(panelBotao); // Adiciona o painel do botão ao layout
    }
}
