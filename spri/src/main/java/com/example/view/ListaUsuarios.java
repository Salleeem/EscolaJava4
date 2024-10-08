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
    private JTable tableAlunos;
    private JTable tableProfessores;
    private DefaultTableModel modelAlunos;
    private DefaultTableModel modelProfessores;

    public ListaUsuarios() {
        // Configurações da JFrame
        setTitle("Lista de Usuários");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criar tabelas para alunos e professores
        String[] columnNamesAlunos = {"ID", "Nome", "CPF", "Ano Escolar", "Turno"};
        String[] columnNamesProfessores = {"ID", "Nome", "CPF", "Matérias", "Salário"};

        modelAlunos = new DefaultTableModel(columnNamesAlunos, 0);
        modelProfessores = new DefaultTableModel(columnNamesProfessores, 0);

        tableAlunos = new JTable(modelAlunos);
        tableProfessores = new JTable(modelProfessores);

        // Carregar dados dos alunos
        carregarDadosAlunos();

        // Carregar dados dos professores
        carregarDadosProfessores();

        // Layout e adição dos componentes
        setLayout(new BorderLayout());
        JScrollPane scrollAlunos = new JScrollPane(tableAlunos);
        JScrollPane scrollProfessores = new JScrollPane(tableProfessores);

        JPanel panelAlunos = new JPanel(new BorderLayout());
        panelAlunos.add(new JLabel("Lista de Alunos"), BorderLayout.NORTH);
        panelAlunos.add(scrollAlunos, BorderLayout.CENTER);

        JPanel panelProfessores = new JPanel(new BorderLayout());
        panelProfessores.add(new JLabel("Lista de Professores"), BorderLayout.NORTH);
        panelProfessores.add(scrollProfessores, BorderLayout.CENTER);

        // Painel para botões
        JPanel panelBotao = new JPanel();
        JButton btnEditarAlunos = new JButton("Editar Alunos");
        JButton btnEditarProfessores = new JButton("Editar Professores");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnVoltar = new JButton("Voltar");
        JButton btnRefresh = new JButton("Refresh"); // Novo botão de Refresh

        btnEditarAlunos.addActionListener(e -> editarAluno());
        btnEditarProfessores.addActionListener(e -> editarProfessor());
        btnExcluir.addActionListener(e -> excluirUsuario());
        btnVoltar.addActionListener(e -> dispose()); // Fecha a janela ao clicar no botão
        btnRefresh.addActionListener(e -> refreshDados()); // Adiciona ação ao botão de Refresh

        panelBotao.add(btnEditarAlunos);
        panelBotao.add(btnEditarProfessores);
        panelBotao.add(btnExcluir);
        panelBotao.add(btnVoltar);
        panelBotao.add(btnRefresh); // Adiciona o botão de Refresh ao painel

        add(panelAlunos, BorderLayout.NORTH);
        add(panelProfessores, BorderLayout.CENTER);
        add(panelBotao, BorderLayout.SOUTH); // Adiciona o painel do botão ao layout
    }

    private void carregarDadosAlunos() {
        modelAlunos.setRowCount(0); // Limpa a tabela antes de recarregar os dados
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
    }

    private void carregarDadosProfessores() {
        modelProfessores.setRowCount(0); // Limpa a tabela antes de recarregar os dados
        List<Professor> professores = ProfessorController.listarProfessores();
        for (Professor professor : professores) {
            List<String> materias = ProfessorController.listarMateriasPorProfessor(professor.getId());
            String materiasStr = String.join(", ", materias);
            modelProfessores.addRow(new Object[]{
                professor.getId(),
                professor.getNome(),
                professor.getCpf(),
                materiasStr,
                professor.getSalario(),
            });
        }
    }

    private void refreshDados() {
        carregarDadosAlunos(); // Recarrega dados dos alunos
        carregarDadosProfessores(); // Recarrega dados dos professores
    }

    private void editarAluno() {
        int selectedRowAlunos = tableAlunos.getSelectedRow();
        if (selectedRowAlunos != -1) {
            int alunoId = (int) tableAlunos.getValueAt(selectedRowAlunos, 0);
            Aluno aluno = AlunoController.buscarAlunoPorId(alunoId);
            new EditarAluno(aluno).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um aluno para editar.");
        }
    }

    private void editarProfessor() {
        int selectedRowProfessores = tableProfessores.getSelectedRow();
        if (selectedRowProfessores != -1) {
            int professorId = (int) tableProfessores.getValueAt(selectedRowProfessores, 0);
            Professor professor = ProfessorController.buscarProfessorPorId(professorId);
            new EditarProfessor(professor).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um professor para editar.");
        }
    }

    private void excluirUsuario() {
        int selectedRowAlunos = tableAlunos.getSelectedRow();
        if (selectedRowAlunos != -1) {
            int alunoId = (int) tableAlunos.getValueAt(selectedRowAlunos, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o aluno?");
            if (confirm == JOptionPane.YES_OPTION) {
                AlunoController.excluirAluno(alunoId);
                modelAlunos.removeRow(selectedRowAlunos);
            }
            return;
        }

        int selectedRowProfessores = tableProfessores.getSelectedRow();
        if (selectedRowProfessores != -1) {
            int professorId = (int) tableProfessores.getValueAt(selectedRowProfessores, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o professor?");
            if (confirm == JOptionPane.YES_OPTION) {
                ProfessorController.excluirProfessor(professorId);
                modelProfessores.removeRow(selectedRowProfessores);
            }
            return;
        }

        JOptionPane.showMessageDialog(this, "Por favor, selecione um usuário para excluir.");
    }
}
