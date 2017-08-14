package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JEditorPane;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAdm extends JFrame {

	private JPanel contentPane;
	private static JFrame telaAdm;

	/**
	 * Launch the application.
	 */
	 public static JFrame getInstance(){
	        if(TelaAdm.telaAdm == null)
	            TelaAdm.telaAdm = new TelaAdm();
	         
	        return TelaAdm.telaAdm;
	         
	    }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdm frame = new TelaAdm();
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
	public TelaAdm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnFuncionrio = new JButton("Funcion\u00E1rio");
		btnFuncionrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FuncionarioGUI().setVisible(true);
			}
		});
		menuBar.add(btnFuncionrio);
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTelaDeAdministrador = new JLabel("Tela de admnistrador");
		lblTelaDeAdministrador.setBounds(144, 97, 126, 14);
		contentPane.add(lblTelaDeAdministrador);
	}
}
