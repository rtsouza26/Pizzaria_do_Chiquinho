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

import dados.ProdutoBD;
import dados.exception.AtualizarProdutoErro;
import dados.exception.BuscaProdutoErro;
import dados.exception.InserirProdutoErro;
import dados.exception.ListarClienteErro;
import dados.exception.ListarProdutoErro;
import dados.exception.RemoverProdutoErro;
import negocio.Fachada;
import negocio.exception.ProdutoExistenteErro;
import negocio.exception.ProdutoInvalidoErro;
import negocio.exception.ProdutoNomeInvalidoErro;
import negocio.exception.ProdutoPrecoInvalidoErro;
import negocio.exception.ProdutoQuantidadeInvalidaErro;
import principal.Cliente;
import principal.Produto;

public class ProdutoGUI extends JFrame {

	private JPanel contentPane;
	private static JFrame telaProd;
	private JTextField textFieldNome;
	private JTextField textFieldQTD;
	private JTextField textFieldPreco;
	private JTextField textFieldPesquisa;
	private ProdutoBD produto;
	private JTable table;
	private List<Produto> produtos;

	/**
	 * Launch the application.
	 */
	public static JFrame getInstance(){
        if(ProdutoGUI.telaProd == null)
        	ProdutoGUI.telaProd = new ProdutoGUI();
         
        return ProdutoGUI.telaProd;
         
    }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutoGUI frame = new ProdutoGUI();
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
	public ProdutoGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 797, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomeDoProduto = new JLabel("Nome do Produto:");
		lblNomeDoProduto.setBounds(22, 30, 105, 14);
		contentPane.add(lblNomeDoProduto);
		
		JLabel lblNewLabel = new JLabel("Quantidade(estoque):");
		lblNewLabel.setBounds(22, 89, 133, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pre\u00E7o:");
		lblNewLabel_1.setBounds(22, 143, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(130, 30, 187, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldQTD = new JTextField();
		textFieldQTD.setBounds(177, 86, 111, 20);
		contentPane.add(textFieldQTD);
		textFieldQTD.setColumns(10);
		
		textFieldPreco = new JTextField();
		textFieldPreco.setBounds(78, 140, 86, 20);
		contentPane.add(textFieldPreco);
		textFieldPreco.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 191, 351, 170);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNovoProduto = new JButton("Novo Produto");
		btnNovoProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldNome.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Informe o Nome do produto");
				}else if(textFieldPreco.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Informe o preço unitário");
				}else if(textFieldQTD.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Informe a quantidade que há no estoque");
				}else{
					try {
						Produto prod = new Produto();
						prod.setNome(textFieldNome.getText());
						prod.setQuantidade(Integer.parseInt(textFieldQTD.getText()));
						prod.setPreco(Double.parseDouble(textFieldPreco.getText()));
						Fachada.getInstance().inserir(prod);
						JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!");
						textFieldNome.setText("");
						textFieldPreco.setText("");
						textFieldQTD.setText("");
					} catch (ClassNotFoundException | SQLException | InserirProdutoErro | ProdutoInvalidoErro | ProdutoExistenteErro | ProdutoPrecoInvalidoErro | ProdutoNomeInvalidoErro | ProdutoQuantidadeInvalidaErro e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
						textFieldNome.setText("");
						textFieldPreco.setText("");
						textFieldQTD.setText("");
					}
				}
				
			}
		});
		btnNovoProduto.setBounds(364, 26, 140, 23);
		contentPane.add(btnNovoProduto);
		
		JButton btnNewButton = new JButton("Buscar Produto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldPesquisa.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Informe o nome do produto para realizar a pesquisa");
				}else{
					try {	
						Produto prod = new Produto();
						prod.setNome(textFieldPesquisa.getText());
						prod = Fachada.getInstance().buscar(prod);
						DefaultTableModel modelo = new DefaultTableModel();
						modelo.setColumnIdentifiers(new String[]{"nome","Quantidade","preço"});
						modelo.addRow(new String[]{prod.getNome()+"" ,String.valueOf(prod.getQuantidade())+"",String.valueOf(prod.getPreco())+""});
						table.setModel(modelo);
						textFieldPesquisa.setText("");
					} catch (ClassNotFoundException | SQLException | ProdutoInvalidoErro | BuscaProdutoErro e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
						textFieldPesquisa.setText("");
					}
				}
			}
		});
		btnNewButton.setBounds(364, 59, 140, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remover Produto");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldPesquisa.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Informe o nome do produto para realizar a remoção");
				}else{
					try {
						Produto prod = new Produto();
						prod.setNome(textFieldPesquisa.getText());
						Fachada.getInstance().remover(prod);
						JOptionPane.showMessageDialog(null, "Remoção realizada com sucesso!");
						textFieldPesquisa.setText("");
					} catch (ClassNotFoundException | SQLException | BuscaProdutoErro | RemoverProdutoErro | ProdutoInvalidoErro e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
						textFieldPesquisa.setText("");
					}
				}
			}
		});
		btnNewButton_1.setBounds(364, 89, 140, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Atualizar produto");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldNome.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Informe o Nome do produto");
				}else if(textFieldPreco.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Informe o preço unitário");
				}else if(textFieldQTD.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Informe a quantidade que há no estoque");
				}else{
					try {
						Produto prod = new Produto();
						prod.setNome(textFieldNome.getText());
						prod.setQuantidade(Integer.parseInt(textFieldQTD.getText()));
						prod.setPreco(Double.parseDouble(textFieldPreco.getText()));
						Fachada.getInstance().atualizar(prod);
						JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!");
						textFieldNome.setText("");
						textFieldPreco.setText("");
						textFieldQTD.setText("");
					} catch (ClassNotFoundException | SQLException | BuscaProdutoErro | AtualizarProdutoErro | ProdutoInvalidoErro  e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
						textFieldNome.setText("");
						textFieldPreco.setText("");
						textFieldQTD.setText("");
					}
				}
			}
		});
		btnNewButton_2.setBounds(364, 123, 140, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Listar Produtos");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel modelo = new DefaultTableModel();
					modelo.setColumnIdentifiers(new String[]{"nome","quantidade","preço","Codigo" });
					 produtos = Fachada.getInstance().listarProduto();
					for (Produto produto: produtos) {
						modelo.addRow(new String[]{produto.getNome()+"",String.valueOf(produto.getQuantidade())+"",String.valueOf(produto.getPreco())+"", String.valueOf(produto.getCodigo())+""});
					}
					
					table.setModel(modelo);
				} catch (ClassNotFoundException | SQLException | ListarProdutoErro  e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnNewButton_3.setBounds(364, 157, 140, 23);
		contentPane.add(btnNewButton_3);
		
		JLabel lblPesquisaDeProdutos = new JLabel("Buscar/Remover(nome):");
		lblPesquisaDeProdutos.setBounds(383, 198, 160, 14);
		contentPane.add(lblPesquisaDeProdutos);
		
		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setBounds(553, 195, 140, 20);
		contentPane.add(textFieldPesquisa);
		textFieldPesquisa.setColumns(10);
		
		JButton btnAtualizarQuantidade = new JButton("Atualizar quantidade");
		btnAtualizarQuantidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldNome.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Informe o Nome do produto");
				}else if(textFieldQTD.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Informe a quantidade que há no estoque");
				}else{
					try {
						Produto prod = new Produto();
						prod.setNome(textFieldNome.getText());
						prod=Fachada.getInstance().buscar(prod);
						prod.setQuantidade(Integer.parseInt(textFieldQTD.getText()));
						Fachada.getInstance().atualizar(prod);
						JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!");
						textFieldNome.setText("");
						textFieldQTD.setText("");
					} catch (ClassNotFoundException | SQLException | BuscaProdutoErro | AtualizarProdutoErro | ProdutoInvalidoErro  e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
						textFieldNome.setText("");
						textFieldQTD.setText("");
					}
				}
			}
		});
		btnAtualizarQuantidade.setBounds(514, 26, 160, 23);
		contentPane.add(btnAtualizarQuantidade);
	}

}
