package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAtendente extends JFrame {

	private JPanel contentPane;
	private static JFrame tela;
	/**
	 * Launch the application.
	 */
	public static JFrame getInstance(){
		if(TelaAtendente.tela==null){
			TelaAtendente.tela= new TelaAtendente();
		}
		return TelaAtendente.tela;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAtendente frame = new TelaAtendente();
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
	public TelaAtendente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTelaDeAtendente = new JLabel("Tela de Atendente");
		lblTelaDeAtendente.setBounds(163, 124, 125, 14);
		contentPane.add(lblTelaDeAtendente);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 424, 21);
		contentPane.add(menuBar);
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ClienteGUI().setVisible(true);
			}
		});
		menuBar.add(btnCliente);
		
		JButton btnPedido = new JButton("Pedido");
		btnPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PedidoGUI().setVisible(true);
			}
		});
		menuBar.add(btnPedido);
		
		JButton btnProduto = new JButton("Produto");
		btnProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProdutoGUI().setVisible(true);
			}
		});
		menuBar.add(btnProduto);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null,"Deseja realmente voltar a tela de Login?","Confirmação" ,JOptionPane.YES_NO_OPTION);
				if(resp == JOptionPane.YES_NO_OPTION){
					new TLogin().setVisible(true);
					dispose();
				}
			}
		});
		menuBar.add(btnVoltar);
	}
}
