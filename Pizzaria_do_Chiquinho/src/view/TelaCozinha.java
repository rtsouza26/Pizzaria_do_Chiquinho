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
import view.TLogin;


public class TelaCozinha extends JFrame {

	private JPanel contentPane;
	private static JFrame tela;

	/**
	 * Launch the application.
	 */
	public static JFrame getInstance(){
		if(TelaCozinha.tela==null){
			TelaCozinha.tela= new TelaCozinha();
		}
		return TelaCozinha.tela;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCozinha frame = new TelaCozinha();
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
	public TelaCozinha() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTelaDeCozinha = new JLabel("Tela de Cozinha");
		lblTelaDeCozinha.setBounds(155, 121, 104, 14);
		contentPane.add(lblTelaDeCozinha);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 318, 21);
		contentPane.add(menuBar);
		
		JButton btnPedido = new JButton("Pedido");
		btnPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PedidoGUI().setVisible(true);
			}
		});
		menuBar.add(btnPedido);
		
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
