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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dados.exception.AtualizarClienteErro;
import dados.exception.BuscarClienteErro;
import dados.exception.InserirClienteErro;
import dados.exception.ListarClienteErro;
import dados.exception.ListarFuncionarioErro;
import dados.exception.RemoverClienteErro;
import negocio.Fachada;
import negocio.exception.ClienteExistenteErro;
import negocio.exception.ClienteInexistenteErro;
import negocio.exception.ClienteInvalidoErro;
import principal.Cliente;
import principal.Funcionario;

public class ClienteGUI extends JFrame {

	private JPanel contentPane;
	private static JFrame tela_cliente;
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private JTextField textFieldEndereco;
	private JTextField textFieldTel;
	private JTable table;
	private JTextField textFieldBusca;
	private List<Cliente> clientes;
	

	/**
	 * Launch the application.
	 */
	public static JFrame getInstance(){
        if(ClienteGUI.tela_cliente == null)
        	ClienteGUI.tela_cliente = new ClienteGUI();
         
        return ClienteGUI.tela_cliente;
         
    }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteGUI frame = new ClienteGUI();
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
	public ClienteGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 654, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 26, 46, 14);
		contentPane.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(52, 23, 173, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 65, 46, 14);
		contentPane.add(lblCpf);
		
		textFieldCpf = new JTextField();
		textFieldCpf.setBounds(52, 62, 130, 20);
		contentPane.add(textFieldCpf);
		textFieldCpf.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(10, 105, 60, 14);
		contentPane.add(lblEndereo);
		
		textFieldEndereco = new JTextField();
		textFieldEndereco.setBounds(86, 102, 271, 20);
		contentPane.add(textFieldEndereco);
		textFieldEndereco.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 150, 60, 14);
		contentPane.add(lblTelefone);
		
		textFieldTel = new JTextField();
		textFieldTel.setBounds(86, 147, 154, 20);
		contentPane.add(textFieldTel);
		textFieldTel.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 224, 333, 175);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldNome.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Escreva o Nome por favor");
				}else if(textFieldEndereco.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Escreva o Endereço por favor");
				}else if(textFieldCpf.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Escreva o CPF por favor");
				}else if(textFieldTel.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Escreva o Telefone por favor");
				}else{
					try {	
						Cliente cliente = new Cliente();
						cliente.setNome(textFieldNome.getText());
						cliente.setCpf(textFieldCpf.getText());
						cliente.setEndereco(textFieldEndereco.getText());
						cliente.setTelefone(textFieldTel.getText());
						Fachada.getInstance().inserir(cliente);
						JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso!");
						textFieldNome.setText("");
						textFieldCpf.setText("");
						textFieldEndereco.setText("");
						textFieldTel.setText("");
					} catch (ClassNotFoundException | SQLException | InserirClienteErro | ClienteExistenteErro
							| ClienteInvalidoErro e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
						textFieldNome.setText("");
						textFieldCpf.setText("");
						textFieldEndereco.setText("");
						textFieldTel.setText("");
					}
				}
			}
		});
		btnCadastrarCliente.setBounds(390, 22, 139, 23);
		contentPane.add(btnCadastrarCliente);
		
		JButton btnNewButton = new JButton("Buscar Cliente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldBusca.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Informe o CPF no campo de pesquisa para realizar a busca");
				}else{
					try {	
						Cliente cliente = new Cliente();
						cliente.setCpf(textFieldBusca.getText());
						cliente = Fachada.getInstance().buscar(cliente);
						DefaultTableModel modelo = new DefaultTableModel();
						modelo.setColumnIdentifiers(new String[]{"nome","CPF","telefone"});
						modelo.addRow(new String[]{cliente.getNome()+"" ,cliente.getCpf()+"",cliente.getTelefone()});
						table.setModel(modelo);
						textFieldBusca.setText("");
					} catch (ClassNotFoundException | SQLException | BuscarClienteErro | ClienteInexistenteErro
							| ClienteInvalidoErro e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.getMessage());
						textFieldBusca.setText("");
					}
				}
			}
		});
		btnNewButton.setBounds(390, 61, 139, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remover Cliente");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldBusca.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Informe o CPF no campo de pesquisa para realizar a remoção");
				}else{
					try {
						Cliente cliente = new Cliente();
						cliente.setCpf(textFieldBusca.getText());
						Fachada.getInstance().remover(cliente);
						JOptionPane.showMessageDialog(null,"Remoção realizada com sucesso!");
						textFieldBusca.setText("");
					} catch (ClassNotFoundException | SQLException | BuscarClienteErro | RemoverClienteErro
							| ClienteInexistenteErro | ClienteInvalidoErro e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,e1.getMessage());
						textFieldBusca.setText("");
					}
					
				}
			}
		});
		btnNewButton_1.setBounds(390, 101, 139, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Atualizar Cliente");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldNome.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Escreva o Nome por favor");
				}else if(textFieldEndereco.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Escreva o Endereço por favor");
				}else if(textFieldCpf.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Escreva o CPF por favor");
				}else if(textFieldTel.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Escreva o Telefone por favor");
				}else{
					try {	
						Cliente cliente = new Cliente();
						cliente.setNome(textFieldNome.getText());
						cliente.setCpf(textFieldCpf.getText());
						cliente.setEndereco(textFieldEndereco.getText());
						cliente.setTelefone(textFieldTel.getText());
						Fachada.getInstance().atualizar(cliente);
						JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
						textFieldNome.setText("");
						textFieldCpf.setText("");
						textFieldEndereco.setText("");
						textFieldTel.setText("");
					} catch (ClassNotFoundException | SQLException | InserirClienteErro
							| ClienteInvalidoErro | BuscarClienteErro | RemoverClienteErro | AtualizarClienteErro | ClienteInexistenteErro e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
						textFieldNome.setText("");
						textFieldCpf.setText("");
						textFieldEndereco.setText("");
						textFieldTel.setText("");
					}
				}
			}
		});
		btnNewButton_2.setBounds(390, 146, 139, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Listar Clientes");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						DefaultTableModel modelo = new DefaultTableModel();
						modelo.setColumnIdentifiers(new String[]{"nome","CPF","telefone"});
						clientes = Fachada.getInstance().listarCliente();
						for (Cliente cliente: clientes) {
							modelo.addRow(new String[]{cliente.getNome()+"" ,cliente.getCpf()+"",cliente.getTelefone()});
						}
						
						table.setModel(modelo);
					} catch (ClassNotFoundException | SQLException  | ListarClienteErro e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
			}
		});
		btnNewButton_3.setBounds(390, 186, 139, 23);
		contentPane.add(btnNewButton_3);
		
		textFieldBusca = new JTextField();
		textFieldBusca.setBounds(390, 268, 189, 20);
		contentPane.add(textFieldBusca);
		textFieldBusca.setColumns(10);
		
		JLabel label = new JLabel("Remover/Buscar por CPF:");
		label.setBounds(427, 243, 152, 14);
		contentPane.add(label);
	}
}
