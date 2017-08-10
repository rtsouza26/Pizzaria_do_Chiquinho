package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dados.exception.AtualizarFuncionarioErro;
import dados.exception.BuscarFuncionarioErro;
import dados.exception.InserirFuncionarioErro;
import dados.exception.RemoverFuncionarioErro;
import negocio.Fachada;
import negocio.exception.FuncionarioExistente;
import negocio.exception.FuncionarioInexistente;
import negocio.exception.FuncionarioInvalido;
import negocio.exception.LoginJaExiste;
import principal.Funcionario;

public class FuncionarioGUI extends JFrame {

	private JPanel contentPane;
	private static JFrame funcionario;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static JFrame getInstance(){
        if(FuncionarioGUI.funcionario == null)
        	FuncionarioGUI.funcionario = new FuncionarioGUI();
         
        return FuncionarioGUI.funcionario;
         
    }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FuncionarioGUI frame = new FuncionarioGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FuncionarioGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(10, 36, 46, 14);
		contentPane.add(lblEndereo);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 61, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 86, 46, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 111, 46, 14);
		contentPane.add(lblTipo);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 205, 46, 14);
		contentPane.add(lblSenha);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(10, 180, 46, 14);
		contentPane.add(lblLogin);
		
		textField = new JTextField();
		textField.setBounds(47, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(66, 33, 231, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(47, 58, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(66, 83, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JRadioButton rdbtnAdmnistrador = new JRadioButton("Administrador");
		rdbtnAdmnistrador.setEnabled(false);
		rdbtnAdmnistrador.setBounds(43, 107, 109, 23);
		contentPane.add(rdbtnAdmnistrador);
		
		JRadioButton rdbtnCozinha = new JRadioButton("Cozinha");
		rdbtnCozinha.setEnabled(false);
		rdbtnCozinha.setBounds(149, 107, 109, 23);
		contentPane.add(rdbtnCozinha);
		
		JRadioButton rdbtnAtendente = new JRadioButton("Atendente");
		rdbtnAtendente.setEnabled(false);
		rdbtnAtendente.setBounds(43, 132, 109, 23);
		contentPane.add(rdbtnAtendente);
		
		textField_4 = new JTextField();
		textField_4.setBounds(47, 177, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(47, 205, 86, 20);
		contentPane.add(passwordField);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionario func = new Funcionario();
				if(textField.getText() != null && textField_1.getText() != null && textField_2.getText() != null && textField_3.getText() != null && textField_4.getText() != null && passwordField.getPassword() != null){
					func.setCpf(textField_2.getText());
					func.setNome(textField.getText());
					func.setEndereco(textField_1.getText());
					func.setTelefone(textField_3.getText());
					func.setLogin(textField_4.getText());
					String senha = new String (passwordField.getPassword());
					func.setSenha(senha);
					if(rdbtnAdmnistrador.isSelected()){
						func.setTipo(rdbtnAdmnistrador.getText());
					}else if(rdbtnAtendente.isSelected()){
						func.setTipo(rdbtnAtendente.getText());
					}else if(rdbtnCozinha.isSelected()){
						func.setTipo(rdbtnCozinha.getText());
					}
					try {
						Fachada.getInstance().inserir(func);
					} catch (ClassNotFoundException | SQLException | FuncionarioInvalido | FuncionarioExistente | InserirFuncionarioErro | LoginJaExiste e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}else{
					JOptionPane.showMessageDialog(null, "Preencha todos os dados por favor");
				}
			}
		});
		btnCadastrar.setBounds(285, 77, 89, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_2.getText() != null){
					try {
						Fachada.getInstance().buscar(textField_2.getText());
					} catch (ClassNotFoundException | SQLException | FuncionarioInexistente | FuncionarioInvalido
							| BuscarFuncionarioErro e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}else{
					JOptionPane.showMessageDialog(null, "Informe o CPF para ser realizado a busca");
				}
			}
		});
		btnBuscar.setBounds(285, 107, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_2.getText() != null){
					try {
						Fachada.getInstance().remover(textField_2.getText());
					} catch (ClassNotFoundException | SQLException | FuncionarioInexistente | FuncionarioInvalido
							| BuscarFuncionarioErro | RemoverFuncionarioErro e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}else{
					JOptionPane.showMessageDialog(null, "Informe o CPF para ser realizado a busca");
				}
			}
		});
		btnRemover.setBounds(285, 142, 89, 23);
		contentPane.add(btnRemover);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionario func = new Funcionario();
				if(textField.getText() != null && textField_1.getText() != null && textField_2.getText() != null && textField_3.getText() != null && textField_4.getText() != null && passwordField.getPassword() != null){
					func.setCpf(textField_2.getText());
					func.setNome(textField.getText());
					func.setEndereco(textField_1.getText());
					func.setTelefone(textField_3.getText());
					func.setLogin(textField_4.getText());
					String senha = new String (passwordField.getPassword());
					func.setSenha(senha);
					if(rdbtnAdmnistrador.isSelected()){
						func.setTipo(rdbtnAdmnistrador.getText());
					}else if(rdbtnAtendente.isSelected()){
						func.setTipo(rdbtnAtendente.getText());
					}else if(rdbtnCozinha.isSelected()){
						func.setTipo(rdbtnCozinha.getText());
					}
					try {
						Fachada.getInstance().atualizar(func);
					} catch (ClassNotFoundException | SQLException | FuncionarioInvalido | InserirFuncionarioErro | FuncionarioInexistente | RemoverFuncionarioErro | BuscarFuncionarioErro | AtualizarFuncionarioErro e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}else{
					JOptionPane.showMessageDialog(null, "Preencha todos os dados por favor");
				}
			}
		});
		btnAtualizar.setBounds(285, 176, 89, 23);
		contentPane.add(btnAtualizar);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Listar funcionarios
			}
		});
		btnListar.setBounds(285, 204, 89, 23);
		contentPane.add(btnListar);
	}

	
}