package com.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import com.example.model.Materia; // Certifique-se de importar o modelo Materia
import com.example.repository.MateriaRepository; // Importa a classe do repositório

public class PaginaInicial extends JFrame {

    private List<Materia> materias; // Lista de matérias

    public PaginaInicial() {
        setTitle("Página Inicial");
        setSize(400, 300); // Tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Usando BorderLayout

        // Puxa as matérias quando a aplicação abre
        materias = obterMaterias();

        // Painel para os botões com FlowLayout
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centraliza os botões com espaçamento

        // Botão para cadastro de aluno
        JButton cadastroAlunoButton = new JButton("Cadastro de Aluno");
        cadastroAlunoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroAluno(); // Abre a tela de cadastro de alunos
                dispose(); // Fecha a página inicial
            }
        });
        panel.add(cadastroAlunoButton);

        // Botão para cadastro de matéria
        JButton cadastroMateriaButton = new JButton("Cadastro de Matéria");
        cadastroMateriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroMateria(); // Abre a tela de cadastro de matérias
                dispose(); // Fecha a página inicial
            }
        });
        panel.add(cadastroMateriaButton);

        // Botão para cadastro de professor
        // Botão para cadastro de professor
        JButton cadastroProfessorButton = new JButton("Cadastro de Professor");
        cadastroProfessorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroProfessor(materias, obterConexao(), PaginaInicial.this); // Passa a referência da página
                                                                                     // inicial
                dispose(); // Fecha a página inicial
            }
        });
        add(cadastroProfessorButton);

        panel.add(cadastroProfessorButton);

        // Adiciona o painel de botões ao centro do BorderLayout
        add(panel, BorderLayout.CENTER);

        // Label ou outro componente para preencher espaço na parte inferior
        JLabel label = new JLabel("Escolha uma opção abaixo:", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH); // Adiciona o label na parte inferior

        setVisible(true); // Torna a janela visível
    }

    private List<Materia> obterMaterias() {
        List<Materia> listaMaterias = new ArrayList<>();
        try (Connection connection = obterConexao()) {
            if (connection != null) {
                MateriaRepository materiaRepository = new MateriaRepository(connection);
                listaMaterias = materiaRepository.buscarTodasAsMaterias(); // Busca as matérias do repositório
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Exibe erro caso ocorra
        }

        return listaMaterias; // Retorna a lista de matérias
    }

    private Connection obterConexao() {
        String url = "jdbc:postgresql://localhost:5432/escolaa"; // Substitua pelo nome do seu banco
        String user = "postgres"; // Substitua pelo seu usuário
        String password = "postgres"; // Substitua pela sua senha

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password); // Cria a conexão
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Falha ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }

        return connection; // Retorna a conexão ou null em caso de falha
    }
}
