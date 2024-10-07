package com.example.view;

import com.example.controller.NotaController;
import com.example.model.Aluno;
import com.example.model.Nota;
import com.example.model.Materia;
import com.example.controller.AlunoController;
import com.example.controller.MateriaController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DashboardProfessor extends JFrame {
    private JTable tableAlunos;
    private JTextField txtNota1, txtNota2, txtNota3, txtNota4, txtNota5;
    private JTextField txtBimestre, txtFaltas; // Campos para Bimestre e Faltas
    private JButton btnAtribuirNota, btnVoltar; // Botões de Logout e Voltar
    private JComboBox<Materia> comboMaterias;

    public DashboardProfessor(String nomeProfessor, int idProfessor) {

        setTitle("Dashboard Professor - " + nomeProfessor);
        setSize(600, 500); // Aumentei a altura para acomodar os novos campos
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Adicionando a coluna "ID do Aluno"
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Nome do Aluno", "CPF", "ID do Aluno"}, 0);
        tableAlunos = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableAlunos);
        
        JPanel panelNotas = new JPanel();
        panelNotas.setLayout(new GridLayout(10, 2)); // Ajustei para incluir mais campos
        
        panelNotas.add(new JLabel("Selecionar Matéria:"));
        comboMaterias = new JComboBox<>();
        carregarMaterias();
        panelNotas.add(comboMaterias);
        
        panelNotas.add(new JLabel("Nota 1:"));
        txtNota1 = new JTextField();
        panelNotas.add(txtNota1);
        
        panelNotas.add(new JLabel("Nota 2:"));
        txtNota2 = new JTextField();
        panelNotas.add(txtNota2);
        
        panelNotas.add(new JLabel("Nota 3:"));
        txtNota3 = new JTextField();
        panelNotas.add(txtNota3);
        
        panelNotas.add(new JLabel("Nota 4:"));
        txtNota4 = new JTextField();
        panelNotas.add(txtNota4);
        
        panelNotas.add(new JLabel("Nota 5:"));
        txtNota5 = new JTextField();
        panelNotas.add(txtNota5);
        
        panelNotas.add(new JLabel("Bimestre (1-4):")); // Campo para Bimestre
        txtBimestre = new JTextField();
        panelNotas.add(txtBimestre);
        
        panelNotas.add(new JLabel("Faltas:")); // Campo para Faltas
        txtFaltas = new JTextField();
        panelNotas.add(txtFaltas);
        
        btnAtribuirNota = new JButton("Atribuir Nota");
        panelNotas.add(btnAtribuirNota);
        
        
        // Adicionando o botão de Voltar
        btnVoltar = new JButton("Voltar");
        panelNotas.add(btnVoltar);
        
        btnAtribuirNota.addActionListener(e -> atribuirNota());
        btnVoltar.addActionListener(e -> voltar()); // Ação para o botão de Voltar

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelNotas, BorderLayout.SOUTH);

        carregarAlunos();
    }

    private void carregarAlunos() {
        List<Aluno> alunos = AlunoController.listarAlunos();
        DefaultTableModel model = (DefaultTableModel) tableAlunos.getModel();
        model.setRowCount(0); // Limpa a tabela antes de adicionar novos dados
        for (Aluno aluno : alunos) {
            // Agora adicionamos o ID do aluno na terceira coluna
            model.addRow(new Object[]{aluno.getNome(), aluno.getCpf(), aluno.getId()}); 
        }
    }
    
    private void carregarMaterias() {
        List<Materia> materias = MateriaController.listarMaterias(); 
        if (materias.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma matéria cadastrada.");
        } else {
            for (Materia materia : materias) {
                comboMaterias.addItem(materia);
            }
        }
    }
    
    private void atribuirNota() {
        int selectedRow = tableAlunos.getSelectedRow();
        if (selectedRow != -1) {
            // Obtenha o nome e CPF do aluno selecionado
            String nomeAluno = (String) tableAlunos.getValueAt(selectedRow, 0);
            String cpfAluno = (String) tableAlunos.getValueAt(selectedRow, 1);
            
            // Capture o id do aluno da terceira coluna da tabela
            int idAluno = (int) tableAlunos.getValueAt(selectedRow, 2); 
            
            double[] notas = new double[5];
            try {
                notas[0] = Double.parseDouble(txtNota1.getText());
                notas[1] = Double.parseDouble(txtNota2.getText());
                notas[2] = Double.parseDouble(txtNota3.getText());
                notas[3] = Double.parseDouble(txtNota4.getText());
                notas[4] = Double.parseDouble(txtNota5.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira notas válidas.");
                return;
            }
            
            // Validação das notas
            for (double nota : notas) {
                if (nota < 0 || nota > 10) {
                    JOptionPane.showMessageDialog(this, "As notas devem estar entre 0 e 10.");
                    return;
                }
            }
        
            int bimestre;
            try {
                bimestre = Integer.parseInt(txtBimestre.getText());
                if (bimestre < 1 || bimestre > 4) {
                    JOptionPane.showMessageDialog(this, "O bimestre deve estar entre 1 e 4.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um bimestre válido.");
                return;
            }
        
            int faltas;
            try {
                faltas = Integer.parseInt(txtFaltas.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um número válido para faltas.");
                return;
            }
        
            Nota nota = new Nota();
            Aluno aluno = new Aluno();
            aluno.setId(idAluno); // Define o id do aluno
            aluno.setNome(nomeAluno);
            aluno.setCpf(cpfAluno);
            nota.setAluno(aluno);
        
            Materia materiaSelecionada = (Materia) comboMaterias.getSelectedItem();
            if (materiaSelecionada == null) {
                JOptionPane.showMessageDialog(this, "Selecione uma matéria válida.");
                return;
            }
            nota.setMateria(materiaSelecionada);
        
            nota.setNotasBimestre(notas);
            nota.setBimestre(bimestre); // Define o bimestre
            nota.setFaltas(faltas);  // Define as faltas
        
            NotaController.atribuirNota(nota);
            JOptionPane.showMessageDialog(this, "Nota atribuída com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um aluno para atribuir nota.");
        }
    }


    private void voltar() {
        // Aqui você pode implementar a lógica para voltar à tela anterior
        dispose(); // Fecha a janela atual
        new LoginProfessor().setVisible(true); // Substitua 'TelaAnterior' pela classe da tela que deseja abrir
    }
}
