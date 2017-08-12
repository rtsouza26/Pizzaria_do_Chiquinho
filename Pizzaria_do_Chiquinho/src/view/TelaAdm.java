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
				FuncionarioGUI.getInstance().setVisible(true);
			}
		});
		menuBar.add(btnFuncionrio);
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteGUI.getInstance().setVisible(true);
			}
		});
		menuBar.add(btnCliente);
		
		JButton btnPedido = new JButton("Pedido");
		btnPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoGUI.getInstance().setVisible(true);
			}
		});
		menuBar.add(btnPedido);
		
		JButton btnProduto = new JButton("Produto");
		btnProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoGUI.getInstance().setVisible(true);
			}
		});
		menuBar.add(btnProduto);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTelaDeAdmnistrador = new JLabel("Tela de admnistrador");
		lblTelaDeAdmnistrador.setBounds(144, 97, 126, 14);
		contentPane.add(lblTelaDeAdmnistrador);
	}
}
