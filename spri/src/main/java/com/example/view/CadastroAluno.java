package com.example.view;

import com.example.controller.AlunoController;
import com.example.model.Aluno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroAluno extends JFrame {

    // Componentes da interface
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtAnoEscolar;
    private JTextField txtTurno;
    private JPasswordField txtSenha;

    public CadastroAluno() {
        setTitle("Cadastro de Aluno");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // Labels e Campos de Texto
        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField();
        JLabel lblCpf = new JLabel("CPF:");
        txtCpf = new JTextField();
        JLabel lblAnoEscolar = new JLabel("Ano Escolar:");
        txtAnoEscolar = new JTextField();
        JLabel lblTurno = new JLabel("Turno:");
        txtTurno = new JTextField();
        JLabel lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField();

        // Botão para cadastrar
        JButton btnCadastrar = new JButton("Cadastrar");

        // Ação do botão
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAluno(); // Método para cadastrar o aluno
            }
        });

        // Adicionando componentes ao painel
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(lblCpf);
        panel.add(txtCpf);
        panel.add(lblAnoEscolar);
        panel.add(txtAnoEscolar);
        panel.add(lblTurno);
        panel.add(txtTurno);
        panel.add(lblSenha);
        panel.add(txtSenha);
        panel.add(new JLabel()); // Espaço vazio
        panel.add(btnCadastrar);

        add(panel);
    }

    // Método para cadastrar o aluno
    private void cadastrarAluno() {
        // Obter dados dos campos de texto
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String anoEscolar = txtAnoEscolar.getText();
        String turno = txtTurno.getText();
        String senha = new String(txtSenha.getPassword());

        // Verificar se todos os campos foram preenchidos
        if (nome.isEmpty() || cpf.isEmpty() || anoEscolar.isEmpty() || turno.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            return;
        }

        // Criar objeto Aluno
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setAnoEscolar(anoEscolar);
        aluno.setTurno(turno);
        aluno.setSenha(senha);

        // Chamar o controlador para salvar o aluno no banco de dados
        AlunoController.cadastrarAluno(aluno);

        // Exibir mensagem de sucesso
        JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");

        // Limpar campos
        txtNome.setText("");
        txtCpf.setText("");
        txtAnoEscolar.setText("");
        txtTurno.setText("");
        txtSenha.setText("");
    }
}
