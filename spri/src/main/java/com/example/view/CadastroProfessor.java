package com.example.view;

import com.example.controller.MateriaController;
import com.example.controller.ProfessorController;
import com.example.model.Materia;
import com.example.model.Professor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CadastroProfessor extends JFrame {

    private JTextField txtNome;
    private JTextField txtCpf;
    private JComboBox<Materia> cbMateria;
    private JTextField txtSalario;
    private JPasswordField txtSenha;
    private JButton btnCadastrar;

    public CadastroProfessor() {
        setTitle("Cadastro de Professor");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 30, 80, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(120, 30, 150, 25);
        add(txtNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(30, 70, 80, 25);
        add(lblCpf);

        txtCpf = new JTextField();
        txtCpf.setBounds(120, 70, 150, 25);
        add(txtCpf);

        JLabel lblMateria = new JLabel("Matéria:");
        lblMateria.setBounds(30, 110, 80, 25);
        add(lblMateria);

        cbMateria = new JComboBox<>();
        List<Materia> materias = MateriaController.listarMaterias();
        for (Materia materia : materias) {
            cbMateria.addItem(materia);
        }
        cbMateria.setBounds(120, 110, 150, 25);
        add(cbMateria);

        JLabel lblSalario = new JLabel("Salário:");
        lblSalario.setBounds(30, 150, 80, 25);
        add(lblSalario);

        txtSalario = new JTextField();
        txtSalario.setBounds(120, 150, 150, 25);
        add(txtSalario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(30, 190, 80, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(120, 190, 150, 25);
        add(txtSenha);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(120, 230, 150, 30);
        add(btnCadastrar);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Professor professor = new Professor();
                professor.setNome(txtNome.getText());
                professor.setCpf(txtCpf.getText());
                professor.setMateria((Materia) cbMateria.getSelectedItem());
                professor.setSalario(Double.parseDouble(txtSalario.getText()));
                professor.setSenha(new String(txtSenha.getPassword()));

                ProfessorController.cadastrarProfessor(professor);
                JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso!");
            }
        });
    }
}
