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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dados.ClienteBD;
import dados.FuncionarioBD;
import dados.ProdutoBD;
import dados.exception.BuscaProdutoErro;
import dados.exception.BuscarClienteErro;
import dados.exception.BuscarFuncionarioErro;
import dados.exception.ListarFuncionarioErro;
import dados.exception.RemoverItem_pedidoErro;
import negocio.Fachada;
import principal.Cliente;
import principal.Funcionario;
import principal.Itens_pedido;
import principal.Pedido;
import principal.Produto;

public class PedidoGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static JFrame pedi;
	private JTextField textFieldCpfcliente;
	private JTextField textFieldCpfFunc;
	private JTextField textFieldObs;
	private JTextField textFieldProds;
	private JTextField textFieldTotal;
	private JTextField textFieldPesquisa;
	private JRadioButton rdbtnNaCozinha;
	private JRadioButton rdbtnACaminho;
	private JRadioButton rdbtnEspera;
	private JRadioButton rdbtnEntregue;
	private JRadioButton rdbtnMercadoriaExtraviada;
	private ProdutoBD produto;
	private FuncionarioBD funcionariobd;
	private ClienteBD clientebd;
	private Itens_pedido itens;
	private double total=0;
	private List<Pedido> pedidos;

	/**
	 * Launch the application.
	 */
	public static JFrame getInstance(){
        if(PedidoGUI.pedi == null)
        	PedidoGUI.pedi = new PedidoGUI();
         
        return PedidoGUI.pedi;
         
    }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PedidoGUI frame = new PedidoGUI();
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
	public PedidoGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 249, 594, 235);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("CPF Cliente:");
		lblNewLabel.setBounds(28, 11, 64, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblCpfFuncionario = new JLabel("CPF Funcionario:");
		lblCpfFuncionario.setBounds(28, 39, 81, 14);
		contentPane.add(lblCpfFuncionario);
		
		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o:");
		lblObservao.setBounds(28, 72, 64, 14);
		contentPane.add(lblObservao);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(28, 109, 46, 14);
		contentPane.add(lblStatus);
		
		textFieldCpfcliente = new JTextField();
		textFieldCpfcliente.setBounds(102, 8, 145, 20);
		contentPane.add(textFieldCpfcliente);
		textFieldCpfcliente.setColumns(10);
		
		textFieldCpfFunc = new JTextField();
		textFieldCpfFunc.setBounds(119, 36, 128, 20);
		contentPane.add(textFieldCpfFunc);
		textFieldCpfFunc.setColumns(10);
		
		textFieldObs = new JTextField();
		textFieldObs.setBounds(102, 69, 210, 20);
		contentPane.add(textFieldObs);
		textFieldObs.setColumns(10);
		
		rdbtnNaCozinha = new JRadioButton("Na Cozinha");
		rdbtnNaCozinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnACaminho.setSelected(false);
				rdbtnEspera.setSelected(false);
				rdbtnEntregue.setSelected(false);
				rdbtnMercadoriaExtraviada.setSelected(false);
			}
		});
		rdbtnNaCozinha.setBounds(86, 105, 109, 23);
		contentPane.add(rdbtnNaCozinha);
		
		rdbtnACaminho = new JRadioButton("A caminho");
		rdbtnACaminho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNaCozinha.setSelected(false);
				rdbtnEspera.setSelected(false);
				rdbtnEntregue.setSelected(false);
				rdbtnMercadoriaExtraviada.setSelected(false);
			}
		});
		rdbtnACaminho.setBounds(86, 149, 109, 23);
		contentPane.add(rdbtnACaminho);
		
		rdbtnEspera = new JRadioButton("Espera");
		rdbtnEspera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnEntregue.setSelected(false);
				rdbtnMercadoriaExtraviada.setSelected(false);
				rdbtnNaCozinha.setSelected(false);
				rdbtnACaminho.setSelected(false);
			}
		});
		rdbtnEspera.setBounds(203, 105, 109, 23);
		contentPane.add(rdbtnEspera);
		
		rdbtnEntregue = new JRadioButton("Entregue");
		rdbtnEntregue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMercadoriaExtraviada.setSelected(false);
				rdbtnNaCozinha.setSelected(false);
				rdbtnACaminho.setSelected(false);
				rdbtnEspera.setSelected(false);
			}
		});
		rdbtnEntregue.setBounds(203, 149, 109, 23);
		contentPane.add(rdbtnEntregue);
		
		rdbtnMercadoriaExtraviada = new JRadioButton("Mercadoria extraviada");
		rdbtnMercadoriaExtraviada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNaCozinha.setSelected(false);
				rdbtnACaminho.setSelected(false);
				rdbtnEspera.setSelected(false);
				rdbtnEntregue.setSelected(false);
			}
		});
		rdbtnMercadoriaExtraviada.setBounds(316, 105, 135, 23);
		contentPane.add(rdbtnMercadoriaExtraviada);
		
		JButton btnNovoPedido = new JButton("Novo pedido");
		btnNovoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(total==0){
					JOptionPane.showMessageDialog(null, "Não há mercadorias inseridas");
				}else if(textFieldCpfFunc.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Preencher CPF do funcionário");
				}else if(textFieldCpfcliente.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Preencher CPF do Cliente");
				}else if(!(rdbtnMercadoriaExtraviada.isSelected() || rdbtnEntregue.isSelected() || rdbtnEspera.isSelected() || rdbtnACaminho.isSelected() || rdbtnNaCozinha.isSelected())){
					JOptionPane.showMessageDialog(null, "Marcar um status do pedido");
				}else{
							try {
								Pedido pedido = new Pedido();
								Cliente cliente = new Cliente();
								Funcionario funcionario = new Funcionario();
								funcionario = funcionariobd.buscarFuncBD(textFieldCpfFunc.getText());
								cliente = clientebd.buscarClienBD(textFieldCpfcliente.getText());
								pedido.setObs(textFieldObs.getText());
								pedido.setCliente(cliente);
								pedido.setFunc(funcionario);
								pedido.setTotal(total);
								pedido.setListadeprodutos(itens);
								if(rdbtnMercadoriaExtraviada.isSelected()){
									pedido.setStatus(rdbtnMercadoriaExtraviada.getText());
								}else if(rdbtnEntregue.isSelected()){
									pedido.setStatus(rdbtnEntregue.getText());
								}else if(rdbtnEspera.isSelected()){
									pedido.setStatus(rdbtnEspera.getText());
								}else if(rdbtnACaminho.isSelected()){
									pedido.setStatus(rdbtnACaminho.getText());
								}else{
									pedido.setStatus(rdbtnNaCozinha.getText());
								}
								Fachada.getInstance().inserir(pedido);
								JOptionPane.showMessageDialog(null, "pedido inserido com sucesso!");
								total=0;
								itens=null;
								itens= new Itens_pedido();
								textFieldTotal.setText("");
								textFieldProds.setText("");
							} catch (SQLException | BuscarFuncionarioErro | BuscarClienteErro | ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								total=0;
								itens=null;
								itens= new Itens_pedido();
								textFieldTotal.setText("");
								JOptionPane.showMessageDialog(null, e1.getMessage());
							}
				}
				
			}
		});
		btnNovoPedido.setBounds(506, 7, 122, 23);
		contentPane.add(btnNovoPedido);
		
		JButton btnNewButton = new JButton("Buscar pedido");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldPesquisa.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "informe o código do pedido para realizar a busca");
				}else{
					try {
					Pedido pedidoaux = new Pedido();
					pedidoaux.setCodigo(textFieldPesquisa.getText());
					pedidoaux = Fachada.getInstance().buscar(pedidoaux);
					DefaultTableModel modelo = new DefaultTableModel();
					modelo.setColumnIdentifiers(new String[]{"Codigo","CPF-cliente","CPF-funcio","Total"});
					modelo.addRow(new String[]{pedidoaux.getCodigo() +"",pedidoaux.getCliente().getCpf() +"", pedidoaux.getFunc().getCpf() +"",String.valueOf(pedidoaux.getTotal()) });
					table.setModel(modelo);
					} catch (ClassNotFoundException | SQLException | BuscaProdutoErro | BuscarFuncionarioErro | BuscarClienteErro e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					
				}
			}
		});
		btnNewButton.setBounds(506, 45, 122, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar pedido");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldPesquisa.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "informe o código do pedido para realizar a remoção");
				}else{
					try {
						Pedido pedidoaux = new Pedido();
						pedidoaux.setCodigo(textFieldPesquisa.getText());
						pedidoaux = Fachada.getInstance().buscar(pedidoaux);
						if(pedidoaux.getStatus()==rdbtnACaminho.getText() ||pedidoaux.getStatus()==rdbtnNaCozinha.getText() || pedidoaux.getStatus() == rdbtnEntregue.getText()){
							JOptionPane.showMessageDialog(null, "O status do pedido o impede de ser cancelado");
						}else{
							Fachada.getInstance().remover(pedidoaux);
						}
						JOptionPane.showMessageDialog(null, "Pedido removido com sucesso");
					} catch (ClassNotFoundException | SQLException | BuscaProdutoErro | BuscarFuncionarioErro
							| BuscarClienteErro | RemoverItem_pedidoErro e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		});
		btnNewButton_1.setBounds(506, 79, 122, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Atualizar pedido");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(total==0){
					JOptionPane.showMessageDialog(null, "Não há mercadorias inseridas");
				}else if(textFieldCpfFunc.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Preencher CPF do funcionário");
				}else if(textFieldCpfcliente.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Preencher CPF do Cliente");
				}else if(!(rdbtnMercadoriaExtraviada.isSelected() || rdbtnEntregue.isSelected() || rdbtnEspera.isSelected() || rdbtnACaminho.isSelected() || rdbtnNaCozinha.isSelected())){
					JOptionPane.showMessageDialog(null, "Marcar um status do pedido");
				}else{
							try {
								Pedido pedido = new Pedido();
								Cliente cliente = new Cliente();
								Funcionario funcionario = new Funcionario();
								funcionario = funcionariobd.buscarFuncBD(textFieldCpfFunc.getText());
								cliente = clientebd.buscarClienBD(textFieldCpfcliente.getText());
								pedido.setObs(textFieldObs.getText());
								pedido.setCliente(cliente);
								pedido.setFunc(funcionario);
								pedido.setTotal(total);
								pedido.setListadeprodutos(itens);
								if(rdbtnMercadoriaExtraviada.isSelected()){
									pedido.setStatus(rdbtnMercadoriaExtraviada.getText());
								}else if(rdbtnEntregue.isSelected()){
									pedido.setStatus(rdbtnEntregue.getText());
								}else if(rdbtnEspera.isSelected()){
									pedido.setStatus(rdbtnEspera.getText());
								}else if(rdbtnACaminho.isSelected()){
									pedido.setStatus(rdbtnACaminho.getText());
								}else{
									pedido.setStatus(rdbtnNaCozinha.getText());
								}
								Fachada.getInstance().atualizar(pedido);
								JOptionPane.showMessageDialog(null, "pedido atualizado com sucesso!");
								total=0;
								itens=null;
								itens= new Itens_pedido();
								textFieldTotal.setText("");
								textFieldProds.setText("");
							} catch (SQLException | ClassNotFoundException | BuscaProdutoErro | BuscarFuncionarioErro | BuscarClienteErro | RemoverItem_pedidoErro e1) {
								// TODO Auto-generated catch block
								total=0;
								itens=null;
								itens= new Itens_pedido();
								textFieldTotal.setText("");
								textFieldProds.setText("");
								JOptionPane.showMessageDialog(null, e1.getMessage());
							}
				}
				
			}
		});
		btnNewButton_2.setBounds(506, 125, 122, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Listar pedidos");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						DefaultTableModel modelo = new DefaultTableModel();
						modelo.setColumnIdentifiers(new String[]{"Codigo","CPF-cliente","CPF-funcio","Total"});
						pedidos = Fachada.getInstance().listar();
						for (Pedido pedi: pedidos) {
							
							modelo.addRow(new String[]{pedi.getCodigo() +"",pedi.getCliente().getCpf() +"", pedi.getFunc().getCpf() +"",String.valueOf(pedi.getTotal()) });
						}
						
						table.setModel(modelo);
					} catch (ClassNotFoundException | SQLException | BuscaProdutoErro | BuscarFuncionarioErro | BuscarClienteErro e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
			}
		});
		btnNewButton_3.setBounds(506, 172, 122, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnAdicionarProdutos = new JButton("Adicionar produtos");
		btnAdicionarProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Produto prodaux = new Produto();
					prodaux = produto.buscar(textFieldProds.getText());
					itens.addProduto(prodaux);
					total= total + prodaux.getPreco();
					textFieldTotal.setText(String.valueOf(total));
					JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
				} catch (ClassNotFoundException | SQLException | BuscaProdutoErro e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				
			}
		});
		btnAdicionarProdutos.setBounds(359, 172, 122, 23);
		contentPane.add(btnAdicionarProdutos);
		
		textFieldProds = new JTextField();
		textFieldProds.setBounds(96, 179, 241, 20);
		contentPane.add(textFieldProds);
		textFieldProds.setColumns(10);
		
		JLabel lblProdutos = new JLabel("Produtos:");
		lblProdutos.setBounds(28, 181, 64, 14);
		contentPane.add(lblProdutos);
		
		JLabel lblTotalAPagar = new JLabel("Total a pagar:");
		lblTotalAPagar.setBounds(28, 224, 81, 14);
		contentPane.add(lblTotalAPagar);
		
		textFieldTotal = new JTextField();
		textFieldTotal.setBounds(102, 221, 109, 20);
		contentPane.add(textFieldTotal);
		textFieldTotal.setColumns(10);
		
		JLabel lblEntrarComO = new JLabel("codigo prara remover/buscar:");
		lblEntrarComO.setBounds(472, 224, 156, 14);
		contentPane.add(lblEntrarComO);
		
		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setBounds(662, 221, 86, 20);
		contentPane.add(textFieldPesquisa);
		textFieldPesquisa.setColumns(10);
	}
}
