package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dados.exception.AtualizarFuncionarioErro;
import dados.exception.BuscarFuncionarioErro;
import dados.exception.InserirFuncionarioErro;
import dados.exception.ListarFuncionarioErro;
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
	private JTextField textFieldNome;
	private JTextField textField_Endereco;
	private JTextField textField_Cpf;
	private JTextField textField_Telefone;
	private JTextField textField_Login;
	private JPasswordField passwordField;
	private JTextField textField_5;
	private JRadioButton rdbtnAdministrador;
	private JRadioButton rdbtnCozinha;
	private JRadioButton rdbtnAtendente;
	private JButton btnAtualizar;
	private JScrollPane scroll;
	private JTable table;
	private List<Funcionario> funcio;
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
		setBounds(100, 100, 746, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//contentPane.add(table_funcionario);
		
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
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(47, 8, 86, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textField_Endereco = new JTextField();
		textField_Endereco.setBounds(66, 33, 231, 20);
		contentPane.add(textField_Endereco);
		textField_Endereco.setColumns(10);
		
		textField_Cpf = new JTextField();
		textField_Cpf.setBounds(47, 58, 86, 20);
		contentPane.add(textField_Cpf);
		textField_Cpf.setColumns(10);
		
		textField_Telefone = new JTextField();
		textField_Telefone.setBounds(66, 83, 86, 20);
		contentPane.add(textField_Telefone);
		textField_Telefone.setColumns(10);
		
		textField_Login = new JTextField();
		textField_Login.setBounds(47, 177, 86, 20);
		contentPane.add(textField_Login);
		textField_Login.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(47, 205, 86, 20);
		contentPane.add(passwordField);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionario func = new Funcionario();
				if(!(textFieldNome.getText().isEmpty() || textField_Endereco.getText().isEmpty() || textField_Cpf.getText().isEmpty() || textField_Telefone.getText().isEmpty() || textField_Login.getText().isEmpty() || passwordField.getText().isEmpty() || (!(rdbtnAdministrador.isSelected()) && !(rdbtnAtendente.isSelected()) && !(rdbtnCozinha.isSelected())))){
					func.setCpf(textField_Cpf.getText());
					func.setNome(textFieldNome.getText());
					func.setEndereco(textField_Endereco.getText());
					func.setTelefone(textField_Telefone.getText());
					func.setLogin(textField_Login.getText());
					func.setSenha(passwordField.getText());
					if(rdbtnAdministrador.isSelected()){
						func.setTipo(rdbtnAdministrador.getText());
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
				if(!(textField_5.getText().isEmpty())){
					Funcionario func = new Funcionario();
					func.setCpf(textField_5.getText());
					try {
						Fachada.getInstance().buscar(func);
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
				if(!(textField_5.getText().isEmpty())){
					Funcionario func = new Funcionario();
					func.setCpf(textField_5.getText());
					try {
						Fachada.getInstance().remover(func);
					} catch (ClassNotFoundException | SQLException | FuncionarioInexistente | FuncionarioInvalido
							| BuscarFuncionarioErro | RemoverFuncionarioErro e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}else{
					JOptionPane.showMessageDialog(null, "Informe o CPF para ser realizado a remoção");
				}
			}
		});
		btnRemover.setBounds(285, 142, 89, 23);
		contentPane.add(btnRemover);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				Funcionario func = new Funcionario();
				if(!(textFieldNome.getText().isEmpty() || textField_Endereco.getText().isEmpty() || textField_Cpf.getText().isEmpty() || textField_Telefone.getText().isEmpty() || textField_Login.getText().isEmpty() || passwordField.getText().isEmpty() || (!(rdbtnAdministrador.isSelected()) && !(rdbtnAtendente.isSelected()) && !(rdbtnCozinha.isSelected())))){
					func.setCpf(textField_Cpf.getText());
					func.setNome(textFieldNome.getText());
					func.setEndereco(textField_Endereco.getText());
					func.setTelefone(textField_Telefone.getText());
					func.setLogin(textField_Login.getText());
					func.setSenha(passwordField.getText());
					if(rdbtnAdministrador.isSelected()){
						func.setTipo(rdbtnAdministrador.getText());
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
		String data[][] = { { "Row1/1", "Row1/2", "Row1/3" },
	            { "Row2/1", "Row2/2", "Row2/3" },
	            { "Row3/1", "Row3/2", "Row3/3" },
	            { "Row4/1", "Row4/2", "Row4/3" }, };

	    String header[] = { "Column 1", "Column 2", "Column 3" };
		
		
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Listar funcionarios
				DefaultTableModel modelo = new DefaultTableModel();
				modelo.setColumnIdentifiers(new String[]{"nome","CPF"});
				try {
					funcio = Fachada.getInstance().listarFuncionario();
				} catch (ClassNotFoundException | SQLException | ListarFuncionarioErro e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				for (Funcionario func1: funcio) {
					modelo.addRow(new String[]{func1.getNome()+"" ,func1.getCpf()});
				}
				
				table.setModel(modelo);
				
			}
		});
		btnListar.setBounds(285, 204, 89, 23);
		contentPane.add(btnListar);
		
		textField_5 = new JTextField();
		textField_5.setBounds(611, 33, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblBuscaremooPorCpf = new JLabel("Busca/Remo\u00E7\u00E3o por CPF");
		lblBuscaremooPorCpf.setBounds(456, 36, 145, 14);
		contentPane.add(lblBuscaremooPorCpf);
		
		rdbtnAdministrador = new JRadioButton("Administrador");
		rdbtnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnCozinha.setSelected(false);
				rdbtnAtendente.setSelected(false);
			}
		});
		
		rdbtnAdministrador.setBounds(47, 111, 109, 23);
		contentPane.add(rdbtnAdministrador);
		
		rdbtnCozinha = new JRadioButton("Cozinha");
		rdbtnCozinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAtendente.setSelected(false);
				rdbtnAdministrador.setSelected(false);
			}
		});
		rdbtnCozinha.setBounds(47, 142, 109, 23);
		contentPane.add(rdbtnCozinha);
		
		rdbtnAtendente = new JRadioButton("Atendente");
		rdbtnAtendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAdministrador.setSelected(false);
				rdbtnCozinha.setSelected(false);
			}
		});
		rdbtnAtendente.setBounds(158, 111, 109, 23);
		contentPane.add(rdbtnAtendente);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 283, 327, 192);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		
		
	}
}
