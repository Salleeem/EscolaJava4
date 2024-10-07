package com.example.view;

import com.example.controller.MateriaController;
import com.example.model.Materia;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroMateria extends JFrame {

    private JTextField txtNome;
    private JButton btnCadastrar;

    public CadastroMateria() {
        setTitle("Cadastro de Matéria");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(null);

        JLabel lblNome = new JLabel("Nome da Matéria:");
        lblNome.setBounds(30, 30, 120, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(150, 30, 100, 25);
        add(txtNome);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(100, 100, 100, 30);
        add(btnCadastrar);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Materia materia = new Materia();
                materia.setNome(txtNome.getText());
                MateriaController.cadastrarMateria(materia);
                JOptionPane.showMessageDialog(null, "Matéria cadastrada com sucesso!");
            }
        });
    }
}
