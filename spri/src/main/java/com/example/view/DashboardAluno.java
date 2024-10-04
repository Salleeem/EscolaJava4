package com.example.view;

import javax.swing.*;
import java.awt.*;

public class DashboardAluno extends JFrame {
    public DashboardAluno(String cpf) {
        setTitle("Dashboard do Aluno");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bem-vindo, Aluno! Seu CPF: " + cpf);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);

        // Outros componentes da dashboard podem ser adicionados aqui

        setVisible(true);
    }
}
