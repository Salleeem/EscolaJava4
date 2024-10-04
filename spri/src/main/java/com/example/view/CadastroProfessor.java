package com.example.view;

import com.example.model.Materia;
import com.example.model.Professor;
import com.example.repository.MateriaRepository;
import com.example.repository.ProfessorRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class CadastroProfessor extends JFrame {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField salarioField;
    private JPasswordField senhaField; // Campo para a senha
    private JComboBox<Materia> materiaComboBox;
    private ProfessorRepository professorRepository;
    private PaginaInicial paginaInicial; // Referência para a página inicial

    public CadastroProfessor(List<Materia> materias, Connection connection, PaginaInicial paginaInicial) {
        setTitle("Cadastro de Professor");
        setSize(400, 350); // Aumenta o tamanho para acomodar o novo campo
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10)); // Aumenta para 7 linhas

        // Inicializa o repositório de professores
        professorRepository = new ProfessorRepository(connection);
        this.paginaInicial = paginaInicial; // Armazena a referência da página inicial

        // Campo para nome
        add(new JLabel("Nome:"));
        nomeField = new JTextField();
        add(nomeField);

        // Campo para CPF
        add(new JLabel("CPF:"));
        cpfField = new JTextField();
        add(cpfField);

        // Campo para salário
        add(new JLabel("Salário:"));
        salarioField = new JTextField();
        add(salarioField);

        // Campo para senha
        add(new JLabel("Senha:"));
        senhaField = new JPasswordField(); // Usando JPasswordField para ocultar a senha
        add(senhaField);

        // ComboBox para selecionar a matéria
        add(new JLabel("Matéria:"));
        materiaComboBox = new JComboBox<>();
        for (Materia materia : materias) {
            materiaComboBox.addItem(materia);
        }
        add(materiaComboBox);

        // Botão para cadastrar o professor
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarProfessor();
            }
        });
        add(cadastrarButton);

        // Botão para voltar à página inicial
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paginaInicial.setVisible(true); // Torna a página inicial visível
                dispose(); // Fecha a janela de cadastro
            }
        });
        add(voltarButton); // Adiciona o botão "Voltar" no layout

        setVisible(true); // Torna a janela visível
    }

    private void cadastrarProfessor() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        double salario = Double.parseDouble(salarioField.getText());
        String senha = new String(senhaField.getPassword()); // Obtendo a senha do JPasswordField
        Materia materiaSelecionada = (Materia) materiaComboBox.getSelectedItem();

        Professor professor = new Professor();
        professor.setNome(nome);
        professor.setCpf(cpf);
        professor.setSalario(salario);
        professor.setMateria(materiaSelecionada);
        professor.setSenha(senha); // Define a senha no objeto Professor

        // Salvar professor no banco de dados
        professorRepository.cadastrarProfessor(professor);

        JOptionPane.showMessageDialog(this, "Professor cadastrado com sucesso!");
        clearFields(); // Limpa os campos após o cadastro
    }

    private void clearFields() {
        nomeField.setText("");
        cpfField.setText("");
        salarioField.setText("");
        senhaField.setText(""); // Limpa o campo de senha
        materiaComboBox.setSelectedIndex(0); // Reseta o ComboBox para o primeiro item
    }
}
